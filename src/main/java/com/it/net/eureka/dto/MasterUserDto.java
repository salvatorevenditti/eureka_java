package com.it.net.eureka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MasterUserDto {

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("lastPaymentDate")
    private LocalDateTime lastPaymentDate;

    @JsonProperty("lastPaymentAmount")
    private BigDecimal lastPaymentAmount;

    @JsonProperty("startDate")
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    private LocalDateTime endDate;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDateTime lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public BigDecimal getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setLastPaymentAmount(BigDecimal lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
