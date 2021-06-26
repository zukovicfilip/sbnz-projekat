package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.controller.dtos.LoginDTO;
import sbnz.integracija.example.controller.dtos.LoginResponseDTO;
import sbnz.integracija.example.controller.dtos.RegisterDTO;
import sbnz.integracija.example.model.persistance.User;
import sbnz.integracija.example.service.UserService;
import sbnz.integracija.example.utils.TokenUtils;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO) {
        User user;
        try {
            user = userService.registerUser(registerDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);
        String jwt = TokenUtils.getInstance().generateToken(user.getId(), user.getEmail(), user.getRole());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user.getId(), user.getEmail(), user.getRole(), jwt);

        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }

}
