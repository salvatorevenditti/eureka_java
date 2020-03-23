package com.it.net.eureka.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
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
}
