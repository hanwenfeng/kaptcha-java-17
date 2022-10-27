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
public class ShadowGimpyTest {
    private Properties properties;

    private ShadowGimpy gimpy;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        gimpy = new ShadowGimpy();
    }

    @Test
    public void testGetDistortedImageAppliesShadowToFontAndAddsTwoNoises() throws Exception {
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "GREEN");
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, "YELLOW");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "BLUE");

        Config config = new Config(properties);

        DefaultWordRenderer renderer = new DefaultWordRenderer();
        DefaultBackground background = new DefaultBackground();

        renderer.setConfig(config);
        background.setConfig(config);
        gimpy.setConfig(config);

        BufferedImage imageWithWord = renderer.renderWord("Clarence BELL", 300,
                80);
        BufferedImage imageWithShadow = gimpy.getDistortedImage(imageWithWord);
        BufferedImage imageWithBackground = background.addBackground(imageWithShadow);
        assertNotNull(imageWithBackground);
        TestUtil.writePngImageFile("ShadowGimpy_shadowFontAndTwoNoises", imageWithBackground);
    }
}
