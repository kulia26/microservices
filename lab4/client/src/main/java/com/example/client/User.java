package com.example.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can not be empty")
    @Pattern(regexp = "[A-z]+", message = "Name must be valid, may contain only latin letters")
    private String name;

    @NotBlank(message = "Surname can not be empty")
    @Pattern(regexp = "[A-z]+", message = "Surname must be valid, may contain only latin letters")
    private String surname;

    @NotBlank(message = "Phone can not be empty")
    @Pattern(regexp = "[0-9+]+", message = "Phone must be valid, may contain only numbers and plus sign")
    @Size(min=10, max=20, message = "Phone must be valid, may contain 10-20 numbers")
    private String phone;

    @NotBlank(message = "Country can not be empty")
    @Pattern(regexp = "[A-z+]+", message = "Country must be valid, may contain only latin letters")
    private String country;

    @NotBlank(message = "City can not be empty")
    @Pattern(regexp = "[A-z+]+", message = "City must be valid, may contain only latin letters")
    private String city;

    @NotBlank(message = "Password can not be empty")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least 1 lowercase alphabetical character")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least 1 uppercase alphabetical character")
    @Pattern(regexp = ".{8,}", message = "Password must be eight characters or longer")
    private String password;

    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email not valid")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "Email not valid")
    private String email;

    @NotBlank(message = "Birthday can not be empty")
    @DateTimeFormat
    @Pattern(regexp = "^[0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2}$", message = "Birthday must be valid")
    private String birthday;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
