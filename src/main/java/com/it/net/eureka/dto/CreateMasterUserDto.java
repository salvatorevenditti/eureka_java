package com.it.net.eureka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.User;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateMasterUserDto extends MasterUserDto {

    @NotNull
    @JsonProperty("master")
    private Master master;

    @NotNull
    @JsonProperty("user")
    private User user;

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CreateMasterUserDto{" +
                "master=" + master.toString() +
                ", user=" + user.toString() +
                '}';
    }
}
