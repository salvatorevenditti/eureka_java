package com.it.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class LoginUserDto {

	@NotNull
	@NotBlank
	@Size(min = 6, max = 30)
	@JsonProperty("inputField")
	private String inputField;
	
	@NotNull
	@NotBlank
	@Size(min = 8, max = 16)
	@JsonProperty("password")
	private String password;
	
	@NotNull
	@NotBlank
	@Size(min = 8, max = 16)
	@JsonProperty("confirmPassword")
	private String confirmPassword;
	
}
