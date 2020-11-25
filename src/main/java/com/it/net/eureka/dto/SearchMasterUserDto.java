package com.it.net.eureka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchMasterUserDto {

    @JsonProperty("master")
    private String masterCorrelationId;

    @JsonProperty("user")
    private String userCorrelationId;

    @JsonProperty("masterUser")
    private String masterUserCorrelationId;

    public String getMasterUserCorrelationId() {
        return masterUserCorrelationId;
    }

    public void setMasterUserCorrelationId(String masterUserCorrelationId) {
        this.masterUserCorrelationId = masterUserCorrelationId;
    }

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
