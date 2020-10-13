package com.it.net.eureka.model;

import com.it.net.eureka.utils.Costants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MasterUserTest {

    @InjectMocks
    public MasterUser masterUser;

    @Mock
    public User user;

    @Mock
    public Master master;

    @BeforeAll
    public final void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public final void testInit() throws Exception {
        masterUser.init();
        assertNotNull(masterUser.getCorrelationId());
    }

    @Test
    public final void testToString() throws Exception {
        assertNotNull(masterUser.toString());
    }

    @Test
    public final void testGetterAndSetter() throws Exception {
        masterUser.setCorrelationId(Costants.CORRELATION_UUID);
        assertEquals(Costants.CORRELATION_UUID, masterUser.getCorrelationId());
        masterUser.setEndDate(Costants.DATE_STR_TEST);
        assertEquals(Costants.DATE_STR_TEST, masterUser.getEndDate());
        masterUser.setInsertDate(Costants.DATE_STR_TEST);
        assertEquals(Costants.DATE_STR_TEST, masterUser.getInsertDate());
        masterUser.setLastPaymentAmount(Costants.BIG_DECIMAL_TEST);
        assertEquals(Costants.BIG_DECIMAL_TEST, masterUser.getLastPaymentAmount());
        masterUser.setLastPaymentDate(Costants.DATE_STR_TEST);
        assertEquals(Costants.DATE_STR_TEST, masterUser.getLastPaymentDate());
        masterUser.setMaster(master);
        assertEquals(master, masterUser.getMaster());
        masterUser.setMasterUserId(Costants.INT_TEST);
        assertEquals(Costants.INT_TEST, masterUser.getMasterUserId());
        masterUser.setPrice(Costants.BIG_DECIMAL_TEST);
        assertEquals(Costants.BIG_DECIMAL_TEST, masterUser.getPrice());
        masterUser.setStartDate(Costants.DATE_STR_TEST);
        assertEquals(Costants.DATE_STR_TEST, masterUser.getStartDate());
        masterUser.setUser(user);
        assertEquals(user, masterUser.getUser());
    }
}
