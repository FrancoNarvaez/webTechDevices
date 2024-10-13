package um.edu.ar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import um.edu.ar.service.dto.DispositivoDTO;
import um.edu.ar.service.dto.VentaDTO;

import java.util.List;

@Service
public class CatedraService {

    private static final String BASE_URL = "http://IP-SERVIDOR:8080/api/catedra";

    @Autowired
    private RestTemplate restTemplate;

    public List<DispositivoDTO> getDispositivos() {
        try {
            String url = BASE_URL + "/dispositivos";
            return restTemplate.getForObject(url, List.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle client and server errors
            throw new RuntimeException("Error fetching dispositivos: " + e.getMessage(), e);
        }
    }

    public VentaDTO createVenta(VentaDTO ventaDTO) {
        try {
            String url = BASE_URL + "/vender";
            return restTemplate.postForObject(url, ventaDTO, VentaDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle client and server errors
            throw new RuntimeException("Error creating venta: " + e.getMessage(), e);
        }
    }

    public List<VentaDTO> getVentas() {
        try {
            String url = BASE_URL + "/ventas";
            return restTemplate.getForObject(url, List.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle client and server errors
            throw new RuntimeException("Error fetching ventas: " + e.getMessage(), e);
        }
    }

    public VentaDTO getVentaById(Long id) {
        try {
            String url = BASE_URL + "/venta/" + id;
            return restTemplate.getForObject(url, VentaDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle client and server errors
            throw new RuntimeException("Error fetching venta by id: " + e.getMessage(), e);
        }
    }
}
