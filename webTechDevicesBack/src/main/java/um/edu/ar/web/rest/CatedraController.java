package um.edu.ar.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import um.edu.ar.service.CatedraApiClient;

@RestController
@RequestMapping("/api")
public class CatedraController {

    private final CatedraApiClient catedraApiClient;

    public CatedraController(CatedraApiClient catedraApiClient) {
        this.catedraApiClient = catedraApiClient;
    }

    @GetMapping("/dispositivos")
    public ResponseEntity<String> getDispositivos() {
        String data = catedraApiClient.getDispositivos();
        return ResponseEntity.ok(data);
    }

    @PostMapping("/catedra/vender")
    public ResponseEntity<String> crearVenta(@RequestBody String ventaJson) {
        String result = catedraApiClient.crearVenta(ventaJson);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/catedra/ventas")
    public ResponseEntity<String> getVentas() {
        String data = catedraApiClient.getVentas();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/catedra/venta/{id}")
    public ResponseEntity<String> getVentaById(@PathVariable String id) {
        String data = catedraApiClient.getVentaById(id);
        return ResponseEntity.ok(data);
    }
}
