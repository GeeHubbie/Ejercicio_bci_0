package models.entity;

import org.hibernate.annotations.CreationTimestamp;
import utilities.PasswordEncryptor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Usuario implements Serializable {
    UUID userId;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private Date created;
    private Date lastLogin;
    private List<PhoneNumber> phones;


    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumber> phones) {
        this.phones = phones;
    }

    public UUID getUserId() {
        return userId;
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

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
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

    public Usuario() {

    }
    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
