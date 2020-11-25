package com.it.net.eureka.repo;

import com.it.net.eureka.model.MasterUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterUserRepository extends CrudRepository<MasterUser, Integer> {

    @Query(value = "SELECT MU.* " +
            "FROM MASTER M, " +
            "     [USER] U, " +
            "     MASTER_USER MU " +
            "WHERE M.MASTER_ID = MU.MASTER_ID " +
            "AND U.USER_ID = MU.USER_ID " +
            "AND M.CORRELATION_ID = :correlationId", nativeQuery = true)
    public List<MasterUser> findByMasterCorrelationId(@Param("correlationId") String correlationId);

    @Query(value = "SELECT MU.* " +
            "FROM MASTER M, " +
            "     [USER] U, " +
            "     MASTER_USER MU " +
            "WHERE M.MASTER_ID = MU.MASTER_ID " +
            "AND U.USER_ID = MU.USER_ID " +
            "AND U.CORRELATION_ID = :correlationId", nativeQuery = true)
    public List<MasterUser> findByUserCorrelationId(@Param("correlationId") String correlationId);

    @Query(value = "SELECT MU.* " +
            "FROM MASTER_USER MU " +
            "WHERE MU.CORRELATION_ID = :correlationId", nativeQuery = true)
    public List<MasterUser> findByCorrelationId(@Param("correlationId") String correlationId);

}
