package com.it.net.eureka.model;

import com.it.net.eureka.dto.CreateUserDto;
import com.it.net.eureka.utils.CryptoUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "USER",
		indexes = {
				@Index(columnList = "USER_USERNAME", unique = true),
				@Index(columnList = "USER_EMAIL", unique = true)
				})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", nullable = false, insertable = false, updatable = false)
	private Integer userId;
	
	@Column(name = "USER_USERNAME", nullable = false, insertable = true, length = 12, updatable = true)
	private String userUsername;
	
	@Column(name = "USER_HASH_PASSWORD", nullable = false, insertable = true, length = 200, updatable = true)
	private byte[] userHashPassword;
	
	@Column(name = "USER_SALT_PASSWORD", nullable = false, insertable = true, length = 200, updatable = true)
	private byte[] userSaltPassword;
	
	@Column(name = "USER_EMAIL", nullable = false, insertable = true, length = 50, updatable = true )
	private String userEmail;
	
	@Column(name = "INSERT_DATE", nullable = false, insertable = true, updatable = false)
	private String insertDate;
	
	@Column(name = "LAST_CHANGE_DATE", nullable = false, insertable = true, updatable = true)
	private String lastChangeDate;

	@Column(name = "BIRTHDATE", nullable = true, insertable = true, updatable = false)
	private LocalDate birthDate;

	@Column(name = "CORRELATION_ID", nullable = false, insertable = true, updatable = false)
	private String correlationId;

	public User() {
		this.insertDate = OffsetDateTime.now().toString();
	}

	@PrePersist
	public void init() {
		this.lastChangeDate = OffsetDateTime.now().toString();
	}

	public User mapEntity(CreateUserDto createUserDto) {
		this.birthDate = createUserDto.getBirthDate();
		this.userEmail = createUserDto.getEmail();
		this.userUsername = createUserDto.getUsername();
		this.userSaltPassword = CryptoUtil.generateSalt();
		this.userHashPassword = CryptoUtil.generateHashWithGivenSalt(createUserDto.getPassword(), this.userSaltPassword);
		this.correlationId = UUID.randomUUID().toString();
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public byte[] getUserHashPassword() {
		return userHashPassword;
	}

	public void setUserHashPassword(byte[] userHashPassword) {
		this.userHashPassword = userHashPassword;
	}

	public byte[] getUserSaltPassword() {
		return userSaltPassword;
	}

	public void setUserSaltPassword(byte[] userSaltPassword) {
		this.userSaltPassword = userSaltPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(String lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}
