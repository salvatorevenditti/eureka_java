package com.it.net.eureka.dto.user;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserDto extends UserDto{
	
	@NotNull
	@JsonProperty("registrationDate")
	private OffsetDateTime registrationDate;
	
	@NotNull
	@JsonProperty("lastChangeDate")
	private OffsetDateTime lastChangeDate;
	
	@Past
	@JsonProperty("birthDate")
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	
	@NotNull
	@NotBlank
	@Size(min = 8, max = 16)
	@JsonProperty("confirmPassword")
	private String confirmPassword;

	public OffsetDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(OffsetDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public OffsetDateTime getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(OffsetDateTime lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
