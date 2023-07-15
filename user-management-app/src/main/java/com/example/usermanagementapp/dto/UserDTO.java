package com.example.usermanagementapp.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class UserDTO {

    @Schema(description = "Id of the User. Long.")
    private long id;
    @Schema(description = "First name, cannot be null. Required.")
    private String firstName;

    @Schema(description = "Last name, cannot be null. Required.")
    private String lastName;

    @Schema(description = "Date of birth, cannot be null. Required.")
    private LocalDate dateOfBirth;

    @Schema(description = "Phone number, cannot be null. Required.")
    private String phoneNumber;

    @Schema(description = "Email, cannot be null. Required.")
    private String email;

    public UserDTO() {
    }

    public UserDTO(long id, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public UserDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public UserDTO setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
