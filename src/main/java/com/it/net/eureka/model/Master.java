package com.it.net.eureka.model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "MASTER",
        indexes = {
                @Index(columnList = "MASTER_USERNAME", unique = true)
        })
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MASTER_ID", nullable = false, insertable = false, updatable = false)
    private Integer masterId;

    @Column(name = "MASTER_USERNAME", nullable = false, insertable = true, updatable = true)
    private String masterUsername;

    @Column(name = "MASTER_HASH_PASSWORD", nullable = false, insertable = true, updatable = true)
    private byte[] masterHashPassword;

    @Column(name = "MASTER_SALT_PASSWORD", nullable = false, insertable = true, updatable = true)
    private byte[] masterSaltPassword;

    @Column(name = "MASTER_EMAIL", nullable = false, insertable = true, updatable = true)
    private String masterEmail;

    @Column(name = "IS_ENABLED", nullable = false, insertable = true, updatable = true)
    private boolean isEnabled;

    @Column(name = "CORRELATION_ID", nullable = false, insertable = true, updatable = false)
    private String correlationId;

    @PrePersist
    public void init() {
        this.correlationId = UUID.randomUUID().toString();
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getMasterUsername() {
        return masterUsername;
    }

    public void setMasterUsername(String masterUsername) {
        this.masterUsername = masterUsername;
    }

    public byte[] getMasterHashPassword() {
        return masterHashPassword;
    }

    public void setMasterHashPassword(byte[] masterHashPassword) {
        this.masterHashPassword = masterHashPassword;
    }

    public byte[] getMasterSaltPassword() {
        return masterSaltPassword;
    }

    public void setMasterSaltPassword(byte[] masterSaltPassword) {
        this.masterSaltPassword = masterSaltPassword;
    }

    public String getMasterEmail() {
        return masterEmail;
    }

    public void setMasterEmail(String masterEmail) {
        this.masterEmail = masterEmail;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "Master{" +
                "masterId=" + masterId +
                ", masterUsername='" + masterUsername + '\'' +
                ", masterHashPassword=" + Arrays.toString(masterHashPassword) +
                ", masterSaltPassword=" + Arrays.toString(masterSaltPassword) +
                ", masterEmail='" + masterEmail + '\'' +
                ", isEnabled=" + isEnabled +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
