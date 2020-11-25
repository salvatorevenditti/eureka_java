package com.it.net.eureka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CreateMasterUserDto extends MasterUserDto {

    @NotNull
    @JsonProperty("masterCorrealationId")
    private String masterCorrelationId;

    @NotNull
    @JsonProperty("userCorrelationId")
    private String userCorrelationId;

    public String getMasterCorrelationId() {
        return masterCorrelationId;
    }

    public void setMasterCorrelationId(String masterCorrelationId) {
        this.masterCorrelationId = masterCorrelationId;
    }

    public String getUserCorrelationId() {
        return userCorrelationId;
    }

    public void setUserCorrelationId(String userCorrelationId) {
        this.userCorrelationId = userCorrelationId;
    }
}
