package com.it.net.eureka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MasterDto {

    @NotNull
    @NotBlank
    @Size(min = 6, max = 50)
    @JsonProperty("username")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 16)
    @JsonProperty("password")
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 50)
    @JsonProperty("email")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
