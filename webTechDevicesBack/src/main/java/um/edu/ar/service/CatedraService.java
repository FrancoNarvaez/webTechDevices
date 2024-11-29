package um.edu.ar.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import um.edu.ar.service.dto.DispositivoDTO;
import um.edu.ar.service.dto.VentaDTO;
import um.edu.ar.web.rest.errors.ExternalApiException;

import java.util.List;
import java.util.Optional;

@Service
public class CatedraService {

    private static final Logger log = LoggerFactory.getLogger(CatedraService.class);
    @Value("${external.api.base-url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<DispositivoDTO> getDispositivos() {
        try {
            String url = baseUrl + "/dispositivos";
            Object dispositivo = restTemplate.getForObject(url, List.class);
            log.debug("Dispositivos: {}", dispositivo.toString());
            return (List<DispositivoDTO>) dispositivo;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error fetching dispositivos: " + e.getMessage(), e);
        }
    }

    public VentaDTO createVenta(VentaDTO ventaDTO) {
        try {
            String url = baseUrl + "/vender";
            return restTemplate.postForObject(url, ventaDTO, VentaDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error creating venta: " + e.getMessage(), e);
        }
    }

    public List<VentaDTO> getVentas() {
        try {
            String url = baseUrl + "/ventas";
            return restTemplate.getForObject(url, List.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error fetching ventas: " + e.getMessage(), e);
        }
    }

    public VentaDTO getVentaById(Long id) {
        try {
            String url = baseUrl + "/venta/" + id;
            return restTemplate.getForObject(url, VentaDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error fetching venta by id: " + e.getMessage(), e);
        }
    }

    public DispositivoDTO createDispositivo(@Valid DispositivoDTO dispositivoDTO) {
        try {
            String url = baseUrl + "/dispositivo";
            return restTemplate.postForObject(url, dispositivoDTO, DispositivoDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error creating dispositivo: " + e.getMessage(), e);
        }
    }

    public DispositivoDTO updateDispositivo(Long id, @Valid DispositivoDTO dispositivoDTO) {
        try {
            String url = baseUrl + "/dispositivo/" + id;
            restTemplate.put(url, dispositivoDTO);
            return dispositivoDTO;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error updating dispositivo: " + e.getMessage(), e);
        }
    }

    public Optional<DispositivoDTO> partialUpdateDispositivo(Long id, @NotNull DispositivoDTO dispositivoDTO) {
        try {
            String url = baseUrl + "/dispositivo/" + id;
            restTemplate.patchForObject(url, dispositivoDTO, DispositivoDTO.class);
            return Optional.of(dispositivoDTO);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error partially updating dispositivo: " + e.getMessage(), e);
        }
    }

    public Page<DispositivoDTO> findAllDispositivos(Pageable pageable) {
        try {
            String url = baseUrl + "/dispositivos";
            return restTemplate.getForObject(url, Page.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error fetching all dispositivos: " + e.getMessage(), e);
        }
    }

    public Optional<DispositivoDTO> findDispositivoById(Long id) {
        try {
            String url = baseUrl + "/dispositivo/" + id;
            return Optional.ofNullable(restTemplate.getForObject(url, DispositivoDTO.class));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error fetching dispositivo by id: " + e.getMessage(), e);
        }
    }

    public void deleteDispositivo(Long id) {
        try {
            String url = baseUrl + "/dispositivo/" + id;
            restTemplate.delete(url);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException("Error deleting dispositivo: " + e.getMessage(), e);
        }
    }
}
