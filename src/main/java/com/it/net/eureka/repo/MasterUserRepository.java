package com.it.net.eureka.repo;

import com.it.net.eureka.model.MasterUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterUserRepository extends CrudRepository<MasterUser, Integer> {

}
