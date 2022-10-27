package com.google.code.kaptcha.text.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author cliffano
 */
public class ChineseTextProducerTest {

    @Test
    public void testGetTextReturnsTextWithoutAlphabetChars() {
        ChineseTextProducer producer = new ChineseTextProducer();
        String text = producer.getText();
        assertNotNull(text);
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            assertTrue(character < 'A' || character > 'z');
        }
    }
}
