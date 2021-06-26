package sbnz.integracija.example.service;

import sbnz.integracija.example.controller.dtos.LoginDTO;
import sbnz.integracija.example.controller.dtos.RegisterDTO;
import sbnz.integracija.example.model.persistance.User;

public interface UserService {

    User registerUser(RegisterDTO registerDTO);

    User login(LoginDTO loginDTO);

}
