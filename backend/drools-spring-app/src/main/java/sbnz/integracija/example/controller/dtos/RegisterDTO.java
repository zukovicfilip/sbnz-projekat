package sbnz.integracija.example.controller.dtos;

import sbnz.integracija.example.model.enums.Role;

public class RegisterDTO {

    private String email;

    private String password;

    public RegisterDTO(String email, String password) {
        this.email = email;
        this.password = password;
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

}
