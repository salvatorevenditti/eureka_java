package com.it.net.eureka.repo;

import com.it.net.eureka.model.Master;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.it.net.eureka.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	@Query(value = 
			"SELECT " 
			+ "(CASE WHEN (SELECT count(*) FROM [USER] WHERE USER_USERNAME = :username) <> 0 THEN 'true' "
			+ "ELSE 'false' "  
			+ "END)",
			nativeQuery = true)
	boolean findIfExistsByUsername(@Param("username") String username);

	@Query(value = 
			"SELECT " 
			+ "(CASE WHEN (SELECT count(*) FROM [USER] WHERE USER_EMAIL = :email) <> 0 THEN 'true' "
			+ "ELSE 'false' "  
			+ "END)",
			nativeQuery = true)
	boolean findIfExistsByEmail(@Param("email") String email);
	
	@Query(value = 
			"SELECT * "
			+ "FROM [USER] WHERE USER_USERNAME = :username",
			nativeQuery = true)
	User findByUsername(@Param("username") String username);
	
	@Query(value = 
			"SELECT * "
			+ "FROM [USER] WHERE USER_EMAIL = :email",
			nativeQuery = true)
	User findByEmail(@Param("email") String email);

	@Query(value =
			"SELECT * "
					+ "FROM [USER] WHERE CORRELATION_ID = :correlationId",
			nativeQuery = true)
	Optional<User> findByCorrelationId(@Param("correlationId") String correlationId);

}
