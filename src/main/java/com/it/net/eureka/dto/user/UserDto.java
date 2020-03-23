package com.it.net.eureka.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class UserDto {

	@NotNull
	@NotBlank
	@Size(min = 6, max = 12)
	@JsonProperty("username")
	private String username;
	
	@NotNull
	@NotBlank
	@Size(min = 8, max = 16)
	@JsonProperty("password")
	private String password;
	
	@NotNull
	@NotBlank
	@Size(min = 6, max = 50)
	@JsonProperty("email")
	private String email;
}
