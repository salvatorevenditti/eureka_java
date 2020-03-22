package com.it.example.demo.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.it.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	@Query(value = 
			"SELECT " 
			+ "(CASE WHEN (SELECT count(*) FROM User WHERE USERNAME = :username) <> 0 THEN 'true' "
			+ "ELSE 'false' "  
			+ "END)",
			nativeQuery = true)
	public boolean findByUsername(@Param("username") String username);

	@Query(value = 
			"SELECT " 
			+ "(CASE WHEN (SELECT count(*) FROM User WHERE EMAIL = :email) <> 0 THEN 'true' "
			+ "ELSE 'false' "  
			+ "END)",
			nativeQuery = true)
	public boolean findByEmail(@Param("email") String email);	
}
