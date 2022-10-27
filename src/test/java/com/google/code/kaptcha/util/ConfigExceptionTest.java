package com.google.code.kaptcha.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author cliffano
 */
public class ConfigExceptionTest {

    @Test
    public void testConstructorWithThrowableCause() {
        ConfigException ce = new ConfigException("createdby", "David E. Kelley", new IllegalArgumentException());
        assertEquals("Invalid value 'David E. Kelley' for config parameter 'createdby'.", ce.getMessage());
        assertTrue(ce.getCause() instanceof IllegalArgumentException);
    }

    @Test
    public void testConstructorWithMessage() {
        ConfigException ce = new ConfigException("createdby", "David E. Kelley", "Roster injury list.");
        assertEquals("Invalid value 'David E. Kelley' for config parameter 'createdby'. Roster injury list.", ce.getMessage());
    }

}
