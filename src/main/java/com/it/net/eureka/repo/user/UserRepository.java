package com.it.net.eureka.repo.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.it.net.eureka.model.user.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	@Query(value = 
			"SELECT " 
			+ "(CASE WHEN (SELECT count(*) FROM User WHERE USERNAME = :username) <> 0 THEN 'true' "
			+ "ELSE 'false' "  
			+ "END)",
			nativeQuery = true)
	public boolean findIfExistsByUsername(@Param("username") String username);

	@Query(value = 
			"SELECT " 
			+ "(CASE WHEN (SELECT count(*) FROM User WHERE EMAIL = :email) <> 0 THEN 'true' "
			+ "ELSE 'false' "  
			+ "END)",
			nativeQuery = true)
	public boolean findIfExistsByEmail(@Param("email") String email);	
	
	@Query(value = 
			"SELECT * "
			+ "FROM User WHERE USERNAME = :username",
			nativeQuery = true)
	public User findByUsername(@Param("username") String username);
	
	@Query(value = 
			"SELECT * "
			+ "FROM User WHERE EMAIL = :email",
			nativeQuery = true)
	public User findByEmail(@Param("email") String email);

}
