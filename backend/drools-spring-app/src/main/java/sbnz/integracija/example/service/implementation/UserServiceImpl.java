package sbnz.integracija.example.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.controller.dtos.LoginDTO;
import sbnz.integracija.example.controller.dtos.RegisterDTO;
import sbnz.integracija.example.model.persistance.User;
import sbnz.integracija.example.model.enums.Role;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setRole(Role.BUYER);
        return userRepository.save(user);
    }

    @Override
    public User login(LoginDTO loginDTO) {
        User user = userRepository.getUserByEmail(loginDTO.getEmail());
        if(user.getPassword().equals(loginDTO.getPassword()))
            return user;
        else
            return null;
    }
}
