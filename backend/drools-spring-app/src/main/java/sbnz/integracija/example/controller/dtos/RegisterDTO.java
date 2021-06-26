package sbnz.integracija.example.controller.dtos;

import sbnz.integracija.example.model.enums.Role;

public class RegisterDTO {

    private String email;

    private String password;

    private Role role;

    public RegisterDTO(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public RegisterDTO() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
