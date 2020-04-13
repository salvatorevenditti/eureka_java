package com.it.net.eureka.service.email;

import com.it.net.eureka.model.email.Email;
import com.it.net.eureka.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

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

    public void mapAndSendEmailForNewUser(Email email, User user) {
        email.setTo(user.getEmail());
        email.setFrom(System.getProperty("spring.mail.username"));
        email.setSubject("Welcome to Eureka!");
        email.setText("Hi there! " +
                "We are happy to see you here....");
        //TODO Complete email body and implements messages for other scenarios (create single method)
        this.sendEmail(email);
    }
}
