package com.softuni.springdataautomapping.domains.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterDto {
    private String email;
    private String password;
    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }
@Pattern(regexp = ".+@.+\\..+",message = "Email is not valid")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Password is not valid")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
@NotNull(message = "First name must be not null")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
