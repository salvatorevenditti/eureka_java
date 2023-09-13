package com.it.net.eureka.controller;

import com.it.net.eureka.dto.CreateMasterUserDto;
import com.it.net.eureka.dto.SearchMasterUserDto;
import com.it.net.eureka.dto.UpdateMasterUserDto;
import com.it.net.eureka.model.MasterUser;
import com.it.net.eureka.service.MasterUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/master/user")
@CrossOrigin
public class MasterUserController {

    @Autowired
    private MasterUserService masterUserService;

    @PostMapping
    public ResponseEntity<MasterUser> insert(@RequestBody @Validated CreateMasterUserDto createMasterUserDto) throws NotFoundException {
        MasterUser masterUser = masterUserService.insert(createMasterUserDto);
        return new ResponseEntity<>(masterUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MasterUser>> getMasterUser(@Validated SearchMasterUserDto searchMasterUserDto) throws NotFoundException {
        List<MasterUser> masterUser = masterUserService.search(searchMasterUserDto);
        return new ResponseEntity<>(masterUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<List<MasterUser>> deleteMasterUser(@Validated SearchMasterUserDto searchMasterUserDto) throws NotFoundException {
        List<MasterUser> masterUser = masterUserService.delete(searchMasterUserDto);
        return new ResponseEntity<>(masterUser, HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<List<MasterUser>> updateMasterUser(@Validated UpdateMasterUserDto updateMasterUserDto) throws NotFoundException {
        List<MasterUser> masterUser = masterUserService.update(updateMasterUserDto);
        return new ResponseEntity<>(masterUser, HttpStatus.OK);
    }
}
