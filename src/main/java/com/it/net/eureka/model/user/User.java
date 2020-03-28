package com.it.net.eureka.model.user;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.utils.CryptoUtil;

@Entity
@Table(name = "User",
		indexes = {
				@Index(columnList = "username", unique = true),
				@Index(columnList = "email", unique = true)
				})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userID", nullable = false, insertable = false, updatable = false)
	private Integer userID;
	
	@Column(name = "username", nullable = false, insertable = true, length = 12, updatable = true)
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

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(byte[] hashPassword) {
		this.hashPassword = hashPassword;
	}

	public byte[] getSaltPassword() {
		return saltPassword;
	}

	public void setSaltPassword(byte[] saltPassword) {
		this.saltPassword = saltPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
}
