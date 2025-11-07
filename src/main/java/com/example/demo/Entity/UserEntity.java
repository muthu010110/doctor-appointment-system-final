package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
    name = "users",
    indexes = { @Index(name = "idx_users_email_unique", columnList = "email", unique = true) }
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // use Long consistently

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 191) // safe for many MySQL configs with utf8mb4
    @Column(nullable = false, unique = true, length = 191)
    private String email;

    // Accept on input, never return on output
    @NotBlank
    @Size(min = 8, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 100)
    private String password;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String role = "PATIENT"; // default

    @Size(max = 20)
    @Column(length = 20)
    private String phone;

    // ===== Constructors =====
    public UserEntity() {}

    public UserEntity(Long id, String name, String email, String password, String role, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
    }

    // ===== Getters / Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // password is write-only via @JsonProperty; still needs getter/setter for JPA
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // (Optional) don’t include password in toString
    @Override
    public String toString() {
        return "UserEntity{id=" + id + ", name='" + name + '\'' +
               ", email='" + email + '\'' + ", role='" + role + '\'' +
               ", phone='" + phone + '\'' + '}';
    }
}
