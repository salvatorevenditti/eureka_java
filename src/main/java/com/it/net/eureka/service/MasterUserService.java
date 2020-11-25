package com.it.net.eureka.service;

import com.it.net.eureka.dto.CreateMasterUserDto;
import com.it.net.eureka.dto.SearchMasterUserDto;
import com.it.net.eureka.dto.UpdateMasterUserDto;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.MasterUser;
import com.it.net.eureka.model.User;
import com.it.net.eureka.repo.MasterUserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MasterUserService {

    @Autowired
    private MasterUserRepository masterUserRepository;

    @Autowired
    private MasterService masterService;

    @Autowired
    private UserService userService;

    public MasterUser insert(CreateMasterUserDto createMasterUserDto) throws NotFoundException {
        Master master = masterService.getMaster(createMasterUserDto.getMasterCorrelationId());
        User user = userService.getUser(createMasterUserDto.getUserCorrelationId());
        MasterUser masterUser = new MasterUser();
        masterUser.setMaster(master);
        masterUser.setUser(user);
        masterUser.setPrice(createMasterUserDto.getPrice());
        masterUser.setLastPaymentDate(createMasterUserDto.getLastPaymentDate().toString());
        masterUser.setLastPaymentAmount(createMasterUserDto.getLastPaymentAmount());
        masterUser.setEndDate(createMasterUserDto.getEndDate().toString());
        masterUser.setStartDate(createMasterUserDto.getStartDate().toString());
        masterUser = masterUserRepository.save(masterUser);
        return masterUser;
    }

    public List<MasterUser> search(SearchMasterUserDto searchMasterUserDto) throws NotFoundException {
        if (Objects.nonNull(searchMasterUserDto.getMasterUserCorrelationId()) && !searchMasterUserDto.getMasterUserCorrelationId().isEmpty())
            return masterUserRepository.findByCorrelationId(searchMasterUserDto.getMasterUserCorrelationId());
        else if (Objects.nonNull(searchMasterUserDto.getMasterCorrelationId()) && !searchMasterUserDto.getMasterCorrelationId().isEmpty())
            return masterUserRepository.findByMasterCorrelationId(searchMasterUserDto.getMasterCorrelationId());
        else if (Objects.nonNull(searchMasterUserDto.getUserCorrelationId()) && !searchMasterUserDto.getUserCorrelationId().isEmpty())
            return masterUserRepository.findByUserCorrelationId(searchMasterUserDto.getUserCorrelationId());
        else
            throw new NotFoundException("No links found for Master " + searchMasterUserDto.getMasterCorrelationId() + "and User " + searchMasterUserDto.getUserCorrelationId());
    }

    public List<MasterUser> delete(SearchMasterUserDto searchMasterUserDto) throws NotFoundException {
        List<MasterUser> toDelete = search(searchMasterUserDto);
        for (MasterUser masterUser : toDelete) {
            masterUserRepository.delete(masterUser);
        }
        return toDelete;
    }

    public List<MasterUser> update(UpdateMasterUserDto updateMasterUserDto) throws NotFoundException {
        SearchMasterUserDto searchDto = new SearchMasterUserDto();
        searchDto.setMasterCorrelationId(updateMasterUserDto.getMasterCorrelationId());
        searchDto.setMasterUserCorrelationId(updateMasterUserDto.getCorrelationId());
        List<MasterUser> toUpdate = search(searchDto);
        List<MasterUser> updated = new ArrayList<>();
        for (MasterUser masterUser : toUpdate) {
            if (Objects.nonNull(updateMasterUserDto.getSubscriptionStart()) && !updateMasterUserDto.getSubscriptionStart().isEmpty())
                masterUser.setStartDate(updateMasterUserDto.getSubscriptionStart());
            if (Objects.nonNull(updateMasterUserDto.getSubscriptionEnd()) && !updateMasterUserDto.getSubscriptionEnd().isEmpty())
                masterUser.setEndDate(updateMasterUserDto.getSubscriptionEnd());
            if (Objects.nonNull(updateMasterUserDto.getSubscriptionPrice()))
                masterUser.setPrice(updateMasterUserDto.getSubscriptionPrice());
            if (Objects.nonNull(updateMasterUserDto.getLastSubscriptionPaymentAmount())) {
                masterUser.setLastPaymentAmount(updateMasterUserDto.getLastSubscriptionPaymentAmount());
                masterUser.setLastPaymentDate(LocalDateTime.now().toString());
            }
            masterUser = masterUserRepository.save(masterUser);
            updated.add(masterUser);
        }
        return updated;
    }
}
