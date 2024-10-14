package um.edu.ar.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import um.edu.ar.service.UserService;
import um.edu.ar.service.dto.UserDTO;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/activate")
    public ResponseEntity<Void> activateUser(@RequestBody UserDTO userDTO) {
        userService.activateUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody UserDTO userDTO) {
        String token = userService.authenticateUser(userDTO);
        return ResponseEntity.ok("{\"id_token\": \"" + token + "\"}");
    }
}
