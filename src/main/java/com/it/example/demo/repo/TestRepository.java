package com.it.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.it.example.demo.model.ModelTest;

public interface TestRepository extends CrudRepository<ModelTest, Integer>{

}
