package com.it.net.eureka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginMasterDto {

    @NotNull
    @NotBlank
    @Size(min = 6, max = 30)
    @JsonProperty("inputField")
    private String inputField;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 16)
    @JsonProperty("password")
    private String password;

    public String getInputField() {
        return inputField;
    }

    public void setInputField(String inputField) {
        this.inputField = inputField;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
