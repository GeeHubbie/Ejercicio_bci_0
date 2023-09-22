package main.request;

import main.models.entity.PhoneNumber;

import java.util.List;

public class UsuarioRequest {

    private String name;
    private String email;
    private String password;
    private List<PhoneNumber> userPhones;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public List<PhoneNumber> getUserPhones() {
        return userPhones;
    }
    public void setUserPhones(List<PhoneNumber> userPhones) {
        this.userPhones = userPhones;
    }

    public UsuarioRequest() {
    }
}
