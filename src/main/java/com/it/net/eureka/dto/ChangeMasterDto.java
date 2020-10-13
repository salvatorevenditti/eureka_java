package com.it.net.eureka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;

public class ChangeMasterDto extends MasterDto {

    @Size(min = 8, max = 16)
    @JsonProperty("newPassword")
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
