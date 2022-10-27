package com.google.code.kaptcha.text.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author cliffano
 */
public class FiveLetterFirstNameTextCreatorTest {

    @Test
    public void testGetTextGivesTextWithFiveLetters() {
        FiveLetterFirstNameTextCreator creator = new FiveLetterFirstNameTextCreator();
        assertEquals(5, creator.getText().length());
    }
}
