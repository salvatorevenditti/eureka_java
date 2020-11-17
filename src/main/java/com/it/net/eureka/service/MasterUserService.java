package com.it.net.eureka.service;

import com.it.net.eureka.dto.CreateMasterUserDto;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.MasterUser;
import com.it.net.eureka.model.User;
import com.it.net.eureka.repo.MasterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterUserService {

    @Autowired
    private MasterUserRepository masterUserRepository;

    public MasterUser insert(CreateMasterUserDto createMasterUserDto) {
        MasterUser masterUser = new MasterUser();
        masterUser.setMaster(createMasterUserDto.getMaster());
        masterUser.setUser(createMasterUserDto.getUser());
        masterUser.setPrice(createMasterUserDto.getPrice());
        masterUser.setLastPaymentDate(createMasterUserDto.getLastPaymentDate().toString());
        masterUser.setLastPaymentAmount(createMasterUserDto.getLastPaymentAmount());
        masterUser.setEndDate(createMasterUserDto.getEndDate().toString());
        masterUser.setStartDate(createMasterUserDto.getStartDate().toString());
        masterUser = masterUserRepository.save(masterUser);
        return masterUser;
    }
}
