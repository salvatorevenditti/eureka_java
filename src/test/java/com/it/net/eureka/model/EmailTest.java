package com.it.net.eureka.model;

import com.it.net.eureka.model.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmailTest {

    public static final String TEST = "TEST";

    @InjectMocks
    private Email email;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        email = new Email();
    }

    @Test
    void gettersAndSetters() {
        email.setFrom(TEST);
        email.setSubject(TEST);
        email.setText(TEST);
        email.setTo(TEST);
        assertNotNull(email.getFrom());
        assertNotNull(email.getSubject());
        assertNotNull(email.getText());
        assertNotNull(email.getTo());

    }
}