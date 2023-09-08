package models.entity;

import org.hibernate.annotations.CreationTimestamp;
import utilities.PasswordEncryptor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@Entity
@Table (name="usuarios")
public class Usuario_in_BD implements Serializable {

    @Id
    UUID userId;

    private String name;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    @Convert(converter = PasswordEncryptor.class)
    private String password;

    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;


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

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Usuario_in_BD(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
