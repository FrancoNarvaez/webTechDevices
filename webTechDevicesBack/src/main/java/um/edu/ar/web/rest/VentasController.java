package um.edu.ar.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import um.edu.ar.service.CatedraService;
import um.edu.ar.service.dto.VentaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/catedra")
public class VentasController {

        @Autowired
        private CatedraService catedraService;

        @GetMapping("/ventas")
        public List<VentaDTO> getVentas() {
            return catedraService.getVentas();
        }

        @GetMapping("/venta/{id}")
        public VentaDTO getVentaById(@PathVariable Long id) {
            return catedraService.getVentaById(id);
        }

        @PostMapping("/vender")
        public VentaDTO createVenta(@RequestBody VentaDTO ventaDTO) {
            return catedraService.createVenta(ventaDTO);
        }
}
