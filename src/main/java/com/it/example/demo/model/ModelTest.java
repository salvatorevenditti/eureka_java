package com.it.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ModelTest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "testID")
	private Integer testID;
	
	@Column(name = "testA")
	private String testA;
	
	@Column(name = "testB")
	private String testB;
	
}
