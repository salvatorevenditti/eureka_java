package com.it.net.eureka.service;

import com.it.net.eureka.model.Email;
import com.it.net.eureka.model.EmailType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class EmailService {

    private static Logger log = LoggerFactory.getLogger(EmailService.class);

    private static final String EMAIL_FROM_PROPERTY = "spring.mail.username";

    private static final String EUREKA_TEAM = "The Eureka Team - ";
    private static final String SUBJECT_CHANGE_SUCCESS = " changed successfully!";
    private static final String SUBJECT_CREATE_SUCCESS = "Welcome to our Community!";
    private static final String HI_THERE = "Hi there! ";
    private static final String BODY_CREATE_WELCOME = "We are happy to see you here, ";
    private static final String BODY_CHANGE_PT1 = "Your request about change ";
    private static final String BODY_CHANGE_PT2 = " has been successfully escaped";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage message;

    public void sendEmail(Email email) {
        message.setTo(email.getTo());
        message.setFrom(email.getFrom());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        mailSender.send(message);
    }

    public <T> void mapAndSendEmail(Email email, EmailType type, T t) {
        email.setFrom(System.getProperty(EMAIL_FROM_PROPERTY));
        try {
            Field emailField = t.getClass().getSuperclass().getDeclaredField("email");
            Field usernameField = t.getClass().getSuperclass().getDeclaredField("username");
            emailField.setAccessible(true);
            usernameField.setAccessible(true);
            email.setTo((String) emailField.get(t));
            //TODO SEND CHANGED EMAIL ALERT ON OLD EMAIL
            switch (type) {
                case CREATE:
                    email.setSubject(EUREKA_TEAM + SUBJECT_CREATE_SUCCESS);
                    email.setText(HI_THERE +
                            BODY_CREATE_WELCOME + (String) usernameField.get(t));
                    break;
                case PASSWORD:
                    email.setSubject(EUREKA_TEAM + EmailType.PASSWORD.name() + SUBJECT_CHANGE_SUCCESS);
                    email.setText(HI_THERE +
                            BODY_CHANGE_PT1 + EmailType.PASSWORD.name() + BODY_CHANGE_PT2);
                    break;
                case EMAIL:
                    email.setSubject(EUREKA_TEAM + EmailType.EMAIL.name() + BODY_CHANGE_PT2);
                    email.setText(HI_THERE +
                            BODY_CHANGE_PT1 + EmailType.EMAIL.name() + BODY_CHANGE_PT2);
                    break;
                case USERNAME:
                    email.setSubject(EUREKA_TEAM + EmailType.USERNAME.name() + SUBJECT_CHANGE_SUCCESS);
                    email.setText(HI_THERE +
                            BODY_CHANGE_PT1 + EmailType.USERNAME.name() + BODY_CHANGE_PT2);
                    break;
                default:
                    return;
            }
        }catch(NoSuchFieldException | IllegalAccessException e){
            log.error("An error occurred... ", e);
        }
        sendEmail(email);
    }
}
