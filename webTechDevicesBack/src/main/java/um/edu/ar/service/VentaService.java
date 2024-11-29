package um.edu.ar.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import um.edu.ar.domain.Adicional;
import um.edu.ar.domain.Opcion;
import um.edu.ar.domain.Venta;
import um.edu.ar.repository.VentaRepository;
import um.edu.ar.repository.DispositivoRepository;
import um.edu.ar.service.dto.VentaDTO;
import um.edu.ar.service.mapper.VentaMapper;

/**
 * Service Implementation for managing {@link um.edu.ar.domain.Venta}.
 */
@Service
@Transactional
public class VentaService {

    private static final Logger LOG = LoggerFactory.getLogger(VentaService.class);

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final DispositivoRepository dispositivoRepository;

    private final CatedraApiClient catedraApiClient;
    private final ObjectMapper objectMapper;

    public VentaService(VentaRepository ventaRepository, VentaMapper ventaMapper,
                        DispositivoRepository dispositivoRepository, CatedraApiClient catedraApiClient,
                        ObjectMapper objectMapper) {
        this.ventaRepository = ventaRepository;
        this.ventaMapper = ventaMapper;
        this.dispositivoRepository = dispositivoRepository;
        this.catedraApiClient = catedraApiClient;
        this.objectMapper = objectMapper;
    }

    /**
     * Save a venta.
     *
     * @param ventaDTO the entity to save.
     * @return the persisted entity.
     */
    public VentaDTO save(VentaDTO ventaDTO) {
        LOG.debug("Request to save Venta : {}", ventaDTO);
        Venta venta = ventaMapper.toEntity(ventaDTO);
        venta = ventaRepository.save(venta);
        try {
            String ventaJson = objectMapper.writeValueAsString(ventaDTO);
            catedraApiClient.crearVenta(ventaJson);
        } catch (JsonProcessingException e) {
            LOG.error("Error converting VentaDTO to JSON", e);
        }
        return ventaMapper.toDto(venta);
    }

    public List<VentaDTO> getVentas() {
        LOG.debug("Request to get all Ventas");
        try {
            String ventasJson = catedraApiClient.getVentas(); // editado
            return objectMapper.readValue(ventasJson, new TypeReference<List<VentaDTO>>() {}); // agregado
        } catch (Exception e) {
            LOG.error("Error converting JSON to List<VentaDTO>", e);
            return List.of();
        }
    }

    public VentaDTO getVentaById(Long id) {
        LOG.debug("Request to get Venta by ID : {}", id);
        try {
            String ventaJson = catedraApiClient.getVentaById(id.toString()); // editado
            return objectMapper.readValue(ventaJson, VentaDTO.class); // agregado
        } catch (Exception e) {
            LOG.error("Error converting JSON to VentaDTO", e);
            return null;
        }
    }

    /**
     * Update a venta.
     *
     * @param ventaDTO the entity to save.
     * @return the persisted entity.
     */
    public VentaDTO update(VentaDTO ventaDTO) {
        LOG.debug("Request to update Venta : {}", ventaDTO);
        Venta venta = ventaMapper.toEntity(ventaDTO);
        venta = ventaRepository.save(venta);
        return ventaMapper.toDto(venta);
    }

    /**
     * Partially update a venta.
     *
     * @param ventaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VentaDTO> partialUpdate(VentaDTO ventaDTO) {
        LOG.debug("Request to partially update Venta : {}", ventaDTO);

        return ventaRepository
            .findById(ventaDTO.getId())
            .map(existingVenta -> {
                ventaMapper.partialUpdate(existingVenta, ventaDTO);

                return existingVenta;
            })
            .map(ventaRepository::save)
            .map(ventaMapper::toDto);
    }

    /**
     * Get all the ventas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VentaDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Ventas");
        return ventaRepository.findAll(pageable).map(ventaMapper::toDto);
    }

    /**
     * Get one venta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VentaDTO> findOne(Long id) {
        LOG.debug("Request to get Venta : {}", id);
        return ventaRepository.findById(id).map(ventaMapper::toDto);
    }

    /**
     * Delete the venta by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Venta : {}", id);
        ventaRepository.deleteById(id);
    }


    public BigDecimal calculateTotalPrice(VentaDTO ventaDTO) {
        LOG.debug("Request to calculate total price for Venta : {}", ventaDTO);

        BigDecimal priceBase = getPriceBaseFromDispositivo(ventaDTO.getIdDispositivo());
        BigDecimal personalizacionesSum = getPersonalizacionesSum(ventaDTO.getPersonalizaciones());
        BigDecimal totalPrice = priceBase.add(personalizacionesSum);
        BigDecimal adicionalesSum = getAdicionalesSum(ventaDTO.getAdicionales(), totalPrice);

        BigDecimal finalPrice = totalPrice.add(adicionalesSum);
        LOG.debug("Calculated total price: {}", finalPrice);
        return finalPrice;
    }

    BigDecimal getPriceBaseFromDispositivo(Long id) {
        BigDecimal priceBase = dispositivoRepository.findById(id)
            .map(dispositivo -> dispositivo.getPrecioBase())
            .orElse(BigDecimal.ZERO);
        LOG.debug("Price base from dispositivo {}: {}", id, priceBase);
        return priceBase;
    }

    BigDecimal getPersonalizacionesSum(List<Opcion> personalizaciones) {
        BigDecimal personalizacionesSum = personalizaciones.stream()
            .map(Opcion::getPrecioAdicional)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        LOG.debug("Sum of personalizaciones: {}", personalizacionesSum);
        return personalizacionesSum;
    }

    BigDecimal getAdicionalesSum(List<Adicional> adicionales, BigDecimal totalPrice) {
        if (adicionales == null) {
            LOG.debug("Adicionales list is null");
            return BigDecimal.ZERO;
        }
        BigDecimal adicionalesSum = adicionales.stream()
            .map(adicional -> {
                BigDecimal precioGratis = adicional.getPrecioGratis();
                BigDecimal precio = adicional.getPrecio();
                LOG.debug("Adicional: {}, Precio Gratis: {}, Total Price: {}", precio, precioGratis, totalPrice);
                if (precioGratis.compareTo(BigDecimal.valueOf(-1)) == 0 || totalPrice.compareTo(precioGratis) <= 0) {
                    return precio;
                } else {
                    return BigDecimal.ZERO;
                }
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        LOG.debug("Sum of adicionales: {}", adicionalesSum);
        return adicionalesSum;
    }
}
