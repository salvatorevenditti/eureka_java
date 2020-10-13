package com.it.net.eureka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

public class CreateMasterDto extends MasterDto {

    @NotNull
    @JsonProperty("enabled")
    private boolean enabled;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 16)
    @JsonProperty("confirmPassword")
    private String confirmPassword;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
