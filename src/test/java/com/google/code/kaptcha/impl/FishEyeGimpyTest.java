package com.google.code.kaptcha.impl;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.text.impl.DefaultWordRenderer;
import com.google.code.kaptcha.util.Config;
import com.google.code.kaptcha.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author cliffano
 */
public class FishEyeGimpyTest {
    private Properties properties;

    private FishEyeGimpy gimpy;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        gimpy = new FishEyeGimpy();
    }

    @Test
    public void testGetDistortedImageAppliesShadowToFontAndAddsTwoNoises() throws Exception {
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "CYAN");
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, "BLACK");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Helvetica");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "WHITE");

        Config config = new Config(properties);

        DefaultWordRenderer renderer = new DefaultWordRenderer();
        DefaultBackground background = new DefaultBackground();

        renderer.setConfig(config);
        background.setConfig(config);

        BufferedImage imageWithWord = renderer.renderWord("Alan SHORE", 300, 80);
        BufferedImage imageWithShadow = gimpy.getDistortedImage(imageWithWord);
        BufferedImage imageWithBackground = background.addBackground(imageWithShadow);
        assertNotNull(imageWithBackground);
        TestUtil.writePngImageFile("FishEyeGimpy_fishEyeAndLines", imageWithBackground);
    }
}
