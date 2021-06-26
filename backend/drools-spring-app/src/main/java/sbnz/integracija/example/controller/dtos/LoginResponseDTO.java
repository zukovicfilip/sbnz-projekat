package sbnz.integracija.example.controller.dtos;

import sbnz.integracija.example.model.enums.Role;

import java.util.UUID;

public class LoginResponseDTO {

    private UUID id;

    private String email;

    private Role role;

    private String token;

    public LoginResponseDTO() {

    }

    public LoginResponseDTO(UUID id, String email, Role role, String token) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
