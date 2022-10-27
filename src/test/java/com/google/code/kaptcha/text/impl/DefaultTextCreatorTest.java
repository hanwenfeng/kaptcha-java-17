package com.google.code.kaptcha.text.impl;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author cliffano
 */
public class DefaultTextCreatorTest {
    private DefaultTextCreator creator;

    private Properties properties;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        creator = new DefaultTextCreator();
        creator.setConfig(new Config(properties));
    }

    @Test
    public void testGetTextCreatesRandomTextOfSpecifiedLengthAndOnlyConsistsSpecifiedChars() {
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "abc");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "9");
        String text = creator.getText();
        assertEquals(9, text.length());
        for (int i = 0; i < text.length(); i++) {
            char currChar = text.charAt(i);
            assertTrue(currChar == 'a' || currChar == 'b' || currChar == 'c');
        }
    }

    @Test
    public void testGetTextWithNullConfigValuesGivesDefaultCharsAndLength() {
        String text = creator.getText();
        assertEquals(5, text.length());
        for (int i = 0; i < text.length(); i++) {
            char currChar = text.charAt(i);
            assertTrue("abcde2345678gfynmnpwx".indexOf(currChar) >= 0);
        }
    }
}
