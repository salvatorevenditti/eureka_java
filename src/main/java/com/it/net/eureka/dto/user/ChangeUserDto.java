package com.it.net.eureka.dto.user;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeUserDto extends UserDto {
	
	@Size(min = 8, max = 16)
	@JsonProperty("newPassword")
	private String newPassword;
}
