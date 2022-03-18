package com.stud.accountService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Locale;

@JsonIgnoreProperties(value = "password", allowGetters = false, allowSetters = true)
@Entity
@Table(name = "users")
@Component
public class User {

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    @NotEmpty
    @Pattern(regexp = ".+@acme.com")
    @Column(name = "email")
    private String email;

    @JsonProperty(value = "password")
    @NotEmpty
    @Column(name = "password")
    private String pass;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String role = "USER";

    public User() {
    }

    public User(String name, String lastname, String email, String pass, String role) {
        this.name = name;
        this.lastname = lastname;
        this.email = email.toLowerCase(Locale.ROOT);
        this.pass = pass;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase(Locale.ROOT);
    }

    @JsonIgnore
    public String getPass() {
        return pass;
    }

    public void setPass(String password) {
        this.pass = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
