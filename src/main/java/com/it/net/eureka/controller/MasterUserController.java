package com.it.net.eureka.controller;

import com.it.net.eureka.dto.CreateMasterDto;
import com.it.net.eureka.dto.CreateMasterUserDto;
import com.it.net.eureka.model.Email;
import com.it.net.eureka.model.EmailType;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.MasterUser;
import com.it.net.eureka.repo.MasterUserRepository;
import com.it.net.eureka.service.MasterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/master/user")
public class MasterUserController {

    @Autowired
    private MasterUserService masterUserService;

    @PostMapping
    public ResponseEntity<MasterUser> insert(@RequestBody @Validated CreateMasterUserDto createMasterUserDto) {
        MasterUser masterUser = masterUserService.insert(createMasterUserDto);
        return new ResponseEntity<>(masterUser, HttpStatus.OK);
    }

}
