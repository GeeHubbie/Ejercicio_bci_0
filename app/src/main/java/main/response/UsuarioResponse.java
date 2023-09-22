package main.response;

import java.util.Date;
import java.util.List;
import main.models.entity.PhoneNumber;
public class UsuarioResponse {

    private Date created;
    private Date lastLogin;
    private String token;
    private Boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<PhoneNumber> phoneNumbers;

    public UsuarioResponse(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
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
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(List<PhoneNumber> tlfs) {
        this.phoneNumbers = tlfs;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
