package com.it.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.it.example.demo.model.ModelTest;
import com.it.example.demo.repo.TestRepository;

@Controller
public class TestController {
	
	@Autowired
	private TestRepository repo; 
	
	@GetMapping(value = "/set")
	public ResponseEntity<ModelTest> setModelTest (ModelTest modelTest) throws Exception{
		
		modelTest.setTestA("A");
		modelTest.setTestB("B");
		modelTest = repo.save(modelTest);
		
		return new ResponseEntity<ModelTest>(modelTest, HttpStatus.OK);
	}
	
}
