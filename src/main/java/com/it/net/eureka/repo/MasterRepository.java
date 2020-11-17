package com.it.net.eureka.repo;

import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterRepository extends CrudRepository<Master, Integer> {

    @Query(value =
            "SELECT "
                    + "(CASE WHEN (SELECT count(*) FROM MASTER WHERE MASTER_USERNAME = :username) <> 0 THEN 'true' "
                    + "ELSE 'false' "
                    + "END)",
            nativeQuery = true)
    boolean findIfExistsByUsername(@Param("username") String username);

    @Query(value =
            "SELECT "
                    + "(CASE WHEN (SELECT count(*) FROM MASTER WHERE MASTER_EMAIL = :email) <> 0 THEN 'true' "
                    + "ELSE 'false' "
                    + "END)",
            nativeQuery = true)
    boolean findIfExistsByEmail(@Param("email") String email);

    @Query(value =
            "SELECT * "
                    + "FROM MASTER WHERE MASTER_USERNAME = :username",
            nativeQuery = true)
    Master findByUsername(@Param("username") String username);

    @Query(value =
            "SELECT * "
                    + "FROM MASTER WHERE MASTER_EMAIL = :email",
            nativeQuery = true)
    Master findByEmail(@Param("email") String email);

    @Query(value =
            "SELECT * "
                    + "FROM MASTER WHERE CORRELATION_ID = :correlationId",
            nativeQuery = true)
    Optional<Master> findByCorrelationId(@Param("correlationId") String correlationId);

}
