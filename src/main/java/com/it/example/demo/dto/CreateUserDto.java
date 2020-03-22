package com.it.example.demo.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CreateUserDto {
	
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
	
}
