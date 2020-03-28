package com.it.net.eureka.dto.user;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangeUserDto extends UserDto {
	
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
