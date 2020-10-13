package com.it.net.eureka.model;

import com.it.net.eureka.utils.Costants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MasterTest {

    @InjectMocks
    public Master master;

    @Test
    public final void testInit() throws Exception {
        master.init();
        assertNotNull(master.getCorrelationId());
    }

    @Test
    public final void testToString() throws Exception {
        assertNotNull(master.toString());
    }

    @Test
    public final void testGetterAndSetter() throws Exception {
        master.setMasterId(Costants.INT_TEST);
        assertEquals(Costants.INT_TEST, master.getMasterId());
        master.setMasterEmail(Costants.STR_TEST);
        assertEquals(Costants.STR_TEST, master.getMasterEmail());
        master.setMasterHashPassword(Costants.BYTE_TEST);
        assertEquals(Costants.BYTE_TEST, master.getMasterHashPassword());
        master.setMasterSaltPassword(Costants.BYTE_TEST);
        assertEquals(Costants.BYTE_TEST, master.getMasterSaltPassword());
        master.setMasterUsername(Costants.STR_TEST);
        assertEquals(Costants.STR_TEST, master.getMasterUsername());
        master.setCorrelationId(Costants.CORRELATION_UUID);
        assertEquals(Costants.CORRELATION_UUID, master.getCorrelationId());
        master.setEnabled(Costants.BOOL_TRUE_TEST);
        assertEquals(Costants.BOOL_TRUE_TEST, master.isEnabled());
    }
}
