package com.it.net.eureka.controller;

import com.it.net.eureka.dto.*;
import com.it.net.eureka.model.Email;
import com.it.net.eureka.model.EmailType;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.service.EmailService;
import com.it.net.eureka.service.MasterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/master")
public class MasterAccessController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private EmailService emailService;
    
    @PostMapping
    public ResponseEntity<Master> signUp(@RequestBody @Validated CreateMasterDto createMasterDto) {
        Master master = masterService.createMaster(createMasterDto);
        emailService.mapAndSendEmail(new Email(), EmailType.CREATE, createMasterDto);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Master> logIn(@RequestBody @Validated LoginMasterDto loginMasterDto) {
        Master master = masterService.loginMaster(loginMasterDto);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @PatchMapping(value = "/password")
    public ResponseEntity<Master> changePassword(@RequestBody @Validated ChangeMasterDto changeMasterDto) throws NotFoundException {
        Master master = masterService.changePassword(changeMasterDto);
        emailService.mapAndSendEmail(new Email(), EmailType.PASSWORD, changeMasterDto);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @PatchMapping(value = "/email")
    public ResponseEntity<Master> changeEmail(@RequestBody @Validated ChangeMasterDto changeMasterDto) throws NotFoundException {
        Master master = masterService.changeEmail(changeMasterDto);
        emailService.mapAndSendEmail(new Email(), EmailType.EMAIL, changeMasterDto);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @PatchMapping(value = "/username")
    public ResponseEntity<Master> changeUsername(@RequestBody @Validated ChangeMasterDto changeMasterDto) throws NotFoundException {
        Master master = masterService.changeUsername(changeMasterDto);
        emailService.mapAndSendEmail(new Email(), EmailType.USERNAME, changeMasterDto);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @GetMapping(value = "/{correlationId}")
    public ResponseEntity<Master> getMaster(@RequestParam String correlationId) throws NotFoundException {
        Master master = masterService.getMaster(correlationId);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }
}
