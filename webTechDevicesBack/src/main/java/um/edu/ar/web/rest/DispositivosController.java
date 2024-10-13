package um.edu.ar.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import um.edu.ar.service.CatedraService;
import um.edu.ar.service.dto.DispositivoDTO;

import java.util.List;

@RestController
@RequestMapping("/api/catedra")
public class DispositivosController {

            @Autowired
            private CatedraService catedraService;

            @GetMapping("/dispositivos")
            public List<DispositivoDTO> getDispositivos() {
                return catedraService.getDispositivos();
            }
}
