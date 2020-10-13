package com.it.net.eureka.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "MASTER_USER")
public class MasterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MASTER_USER_ID", insertable = false, updatable = false, nullable = false)
    private Integer masterUserId;

    @Column(name = "CORRELATION_ID", insertable = true, updatable = false, nullable = false)
    private String correlationId;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = true, updatable = false, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "MASTER_ID", referencedColumnName = "MASTER_ID", insertable = true, updatable = false, nullable = false)
    private Master master;

    @Column(name = "INSERT_DATE", insertable = true, updatable = false, nullable = false)
    private String insertDate;

    @Column(name = "START_DATE", insertable = true, updatable = true, nullable = true)
    private String startDate;

    @Column(name = "END_DATE", insertable = true, updatable = true, nullable = true)
    private String endDate;

    @Column(name = "LAST_PAYMENT_DATE", insertable = true, updatable = true, nullable = true)
    private String lastPaymentDate;

    @Column(name = "LAST_PAYMENT_AMOUNT", insertable = true, updatable = true, nullable = true)
    private BigDecimal lastPaymentAmount;

    @Column(name = "PRICE", insertable = true, updatable = true, nullable = true)
    private BigDecimal price;

    public Integer getMasterUserId() {
        return masterUserId;
    }

    public void setMasterUserId(Integer masterUserId) {
        this.masterUserId = masterUserId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public BigDecimal getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setLastPaymentAmount(BigDecimal lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MasterUser{" +
                "masterUserId=" + masterUserId +
                ", correlationId='" + correlationId + '\'' +
                ", user=" + user.toString() +
                ", master=" + master.toString() +
                ", insertDate='" + insertDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", lastPaymentDate='" + lastPaymentDate + '\'' +
                ", lastPaymentAmount=" + lastPaymentAmount +
                ", price=" + price +
                '}';
    }
}
