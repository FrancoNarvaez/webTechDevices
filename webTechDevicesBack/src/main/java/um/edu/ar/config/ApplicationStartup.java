//package um.edu.ar.config;
//
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Component;
//import um.edu.ar.service.DispositivoService;
//
//@Component
//public class ApplicationStartup {
//
//    private final DispositivoService dispositivoService;
//
//    public ApplicationStartup(DispositivoService dispositivoService) {
//        this.dispositivoService = dispositivoService;
//    }
//
//    @PostConstruct
//    public void init() {
//        dispositivoService.findAll(PageRequest.of(0, 10)).forEach(dispositivo -> {
//        // Procesar cada dispositivo
//        System.out.println(dispositivo.toString());
//        });
//    }
//}
