package com.it.net.eureka.service.email;

import com.it.net.eureka.dto.user.ChangeUserDto;
import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.model.email.Email;
import com.it.net.eureka.model.email.EmailType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private SimpleMailMessage message;

    private Email email;
    private

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void sendEmail() {
        emailService.sendEmail(new Email());
    }

    @Test
    void mapAndSendEmail() {
        emailService.mapAndSendEmail(new Email(), EmailType.CREATE_USER, new CreateUserDto());
        emailService.mapAndSendEmail(new Email(), EmailType.PASSWORD, new ChangeUserDto());
        emailService.mapAndSendEmail(new Email(), EmailType.EMAIL, new ChangeUserDto());
        emailService.mapAndSendEmail(new Email(), EmailType.USERNAME, new ChangeUserDto());
        emailService.mapAndSendEmail(new Email(), EmailType.DEFAULT, new ChangeUserDto());
        emailService.mapAndSendEmail(new Email(), EmailType.DEFAULT,  new Email());
    }
}