package com.it.net.eureka.dto;

import java.math.BigDecimal;

public class UpdateMasterUserDto {

    private String correlationId;

    private String masterCorrelationId;

    private String subscriptionStart;

    private String subscriptionEnd;

    private BigDecimal lastSubscriptionPaymentAmount;

    private BigDecimal subscriptionPrice;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getMasterCorrelationId() {
        return masterCorrelationId;
    }

    public void setMasterCorrelationId(String masterCorrelationId) {
        this.masterCorrelationId = masterCorrelationId;
    }

    public String getSubscriptionStart() {
        return subscriptionStart;
    }

    public void setSubscriptionStart(String subscriptionStart) {
        this.subscriptionStart = subscriptionStart;
    }

    public String getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public void setSubscriptionEnd(String subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;
    }

    public BigDecimal getLastSubscriptionPaymentAmount() {
        return lastSubscriptionPaymentAmount;
    }

    public void setLastSubscriptionPaymentAmount(BigDecimal lastSubscriptionPaymentAmount) {
        this.lastSubscriptionPaymentAmount = lastSubscriptionPaymentAmount;
    }

    public BigDecimal getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionPrice(BigDecimal subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }
}
