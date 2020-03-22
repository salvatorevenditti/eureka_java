package com.it.example.demo.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.it.example.demo.dto.CreateUserDto;
import com.it.example.demo.utils.CryptoUtil;

import lombok.Data;

@Entity
@Table(name = "User")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userID", nullable = false, insertable = false, updatable = false)
	private Integer userID;
	
	@Column(name = "username", nullable = false, insertable = true, length = 12, updatable = false)
	private String username;
	
	@Column(name = "hashPassword", nullable = false, insertable = true, length = 200, updatable = true)
	private byte[] hashPassword;
	
	@Column(name = "saltPassword", nullable = false, insertable = true, length = 200, updatable = true)
	private byte[] saltPassword;
	
	@Column(name = "email", nullable = false, insertable = true, length = 50, updatable = true )
	private String email;
	
	@Column(name = "registrationDate", nullable = false, insertable = true, updatable = false)
	private OffsetDateTime registrationDate;
	
	@Column(name = "lastChangeDate", nullable = false, insertable = true, updatable = true)
	private OffsetDateTime lastChangeDate;
	
	@Column(name = "birthDate", nullable = true, insertable = true, updatable = false)
	private LocalDate birthDate;
	
	public User() {
		this.registrationDate = OffsetDateTime.now();
	}
	
	@PrePersist
	public void init() {
		this.lastChangeDate = OffsetDateTime.now();
	}
	
	public User mapEntity(CreateUserDto createUserDto) {
		
		this.birthDate = createUserDto.getBirthDate();
		this.email = createUserDto.getEmail();
		this.username = createUserDto.getUsername();
		this.saltPassword = CryptoUtil.generateSalt();
		this.hashPassword = CryptoUtil.generateHashWithGivenSalt(createUserDto.getPassword(), this.saltPassword);
		
		return this;
	}
}
