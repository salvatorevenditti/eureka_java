package com.it.net.eureka.service;

import com.it.net.eureka.dto.*;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.repo.MasterRepository;
import com.it.net.eureka.utils.CryptoUtil;
import com.it.net.eureka.validator.ChangeMasterValidator;
import com.it.net.eureka.validator.CreateMasterValidator;
import com.it.net.eureka.validator.LoginMasterValidator;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MasterService {

    @Autowired
    private MasterRepository masterRepository;

    @Autowired
    private CreateMasterValidator createMasterValidator;

    @Autowired
    private ChangeMasterValidator changeMasterValidator;

    @Autowired
    private LoginMasterValidator loginMasterValidator;

    private Master master = new Master();

    public Master createMaster(CreateMasterDto createMasterDto) {
        createMasterValidator.validate(createMasterDto);
        master.mapEntity(createMasterDto);
        master = masterRepository.save(master);
        return master;
    }

    public Master changePassword(ChangeMasterDto changeMasterDto) throws NotFoundException {
        changeMasterValidator.validateNewPassword(changeMasterDto);
        master = masterRepository.findByUsername(changeMasterDto.getUsername());
        if (master == null)
            throw new NotFoundException("Username doesn't exists!");
        if (!master.equals(masterRepository.findByEmail(changeMasterDto.getEmail())))
            throw new NotFoundException("Email doesn't exists!");
        changeMasterValidator.checkOldPassword(changeMasterDto, master);
        master.setMasterSaltPassword(CryptoUtil.generateSalt());
        master.setMasterHashPassword(CryptoUtil.generateHashWithGivenSalt(changeMasterDto.getNewPassword(), master.getMasterSaltPassword()));
        return masterRepository.save(master);
    }

    public Master changeUsername(ChangeMasterDto changeMasterDto) throws NotFoundException {
        changeMasterValidator.validateNewUsername(changeMasterDto);
        master = masterRepository.findByEmail(changeMasterDto.getEmail());
        if (master == null)
            throw new NotFoundException("Email doesn't exists!");
        changeMasterValidator.checkOldPassword(changeMasterDto, master);
        master.setMasterUsername(changeMasterDto.getUsername());
        return masterRepository.save(master);
    }

    public Master changeEmail(ChangeMasterDto changeMasterDto) throws NotFoundException {
        changeMasterValidator.validateNewEmail(changeMasterDto);
        master = masterRepository.findByUsername(changeMasterDto.getUsername());
        if (master == null)
            throw new NotFoundException("Username doesn't exists!");
        changeMasterValidator.checkOldPassword(changeMasterDto, master);
        master.setMasterEmail(changeMasterDto.getEmail());
        return masterRepository.save(master);
    }

    public Master getMaster(String correlationId) throws NotFoundException {
        Optional<Master> masterOpt = masterRepository.findByCorrelationId(correlationId);
        master = masterOpt.get();
        if(master == null) throw new NotFoundException("Master not found");
        return master;
    }

    public Master loginMaster(LoginMasterDto loginMasterDto) {
        return loginMasterValidator.validate(loginMasterDto);
    }
}
