package um.edu.ar.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import um.edu.ar.domain.Adicional;
import um.edu.ar.domain.Opcion;
import um.edu.ar.domain.Dispositivo;
import um.edu.ar.repository.DispositivoRepository;
import um.edu.ar.repository.PersonalizacionRepository;
import um.edu.ar.repository.AdicionalRepository;
import um.edu.ar.repository.VentaRepository;
import um.edu.ar.service.dto.VentaDTO;
import um.edu.ar.service.mapper.VentaMapper;

public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private DispositivoRepository dispositivoRepository;

    @Mock
    private PersonalizacionRepository personalizacionRepository;

    @Mock
    private AdicionalRepository adicionalRepository;

    @Mock
    private VentaMapper ventaMapper;

    @InjectMocks
    private VentaService ventaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPriceBaseFromDispositivo() {
        Long dispositivoId = 1L;
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setPrecioBase(BigDecimal.valueOf(1000));
        when(dispositivoRepository.findById(dispositivoId)).thenReturn(Optional.of(dispositivo));

        BigDecimal priceBase = ventaService.getPriceBaseFromDispositivo(dispositivoId);
        assertEquals(BigDecimal.valueOf(1000), priceBase);
    }

    @Test
    public void testGetPersonalizacionesSum() {
        Opcion opcion1 = new Opcion();
        opcion1.setPrecioAdicional(BigDecimal.valueOf(100));
        Opcion opcion2 = new Opcion();
        opcion2.setPrecioAdicional(BigDecimal.valueOf(200));
        List<Opcion> personalizaciones = Arrays.asList(opcion1, opcion2);

        BigDecimal personalizacionesSum = ventaService.getPersonalizacionesSum(personalizaciones);
        assertEquals(BigDecimal.valueOf(300), personalizacionesSum);
    }

    @Test
    public void testGetAdicionalesSum() {
        Adicional adicional1 = new Adicional();
        adicional1.setPrecio(BigDecimal.valueOf(50));
        adicional1.setPrecioGratis(BigDecimal.valueOf(2000));
        Adicional adicional2 = new Adicional();
        adicional2.setPrecio(BigDecimal.valueOf(100));
        adicional2.setPrecioGratis(BigDecimal.valueOf(-1));
        List<Adicional> adicionales = Arrays.asList(adicional1, adicional2);

        BigDecimal totalPrice = BigDecimal.valueOf(1500);
        BigDecimal adicionalesSum = ventaService.getAdicionalesSum(adicionales, totalPrice);
        assertEquals(BigDecimal.valueOf(150), adicionalesSum);
    }

    @Test
    public void testCalculateTotalPrice() {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setIdDispositivo(1L);
        Opcion opcion1 = new Opcion();
        opcion1.setPrecioAdicional(BigDecimal.valueOf(100));
        Opcion opcion2 = new Opcion();
        opcion2.setPrecioAdicional(BigDecimal.valueOf(200));
        ventaDTO.setpersonalizaciones(Arrays.asList(opcion1, opcion2));

        Adicional adicional1 = new Adicional();
        adicional1.setPrecio(BigDecimal.valueOf(50));
        adicional1.setPrecioGratis(BigDecimal.valueOf(2000));
        Adicional adicional2 = new Adicional();
        adicional2.setPrecio(BigDecimal.valueOf(100));
        adicional2.setPrecioGratis(BigDecimal.valueOf(-1));
        ventaDTO.setadicionales(Arrays.asList(adicional1, adicional2));

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setPrecioBase(BigDecimal.valueOf(1000));
        when(dispositivoRepository.findById(1L)).thenReturn(Optional.of(dispositivo));

        BigDecimal totalPrice = ventaService.calculateTotalPrice(ventaDTO);
        assertEquals(BigDecimal.valueOf(1450), totalPrice);
    }
}
