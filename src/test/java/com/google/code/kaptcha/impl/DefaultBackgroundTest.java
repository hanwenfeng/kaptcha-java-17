package com.google.code.kaptcha.impl;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.util.Config;
import com.google.code.kaptcha.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * @author cliffano
 */
public class DefaultBackgroundTest {
    private DefaultBackground background;

    private Properties properties;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        background = new DefaultBackground();
        background.setConfig(new Config(properties));
    }

    @Test
    public void testAddBackgroundWithDifferentColorFromAndToGivesDiagonalGradientBackgroundWithSpecifiedColors() throws Exception {
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "GREEN");
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, "MAGENTA");
        BufferedImage baseImage = TestUtil.createBaseImage();
        BufferedImage imageWithBackground = background.addBackground(baseImage);
        TestUtil.writePngImageFile("DefaultBackground_gradientBackgroundGreenMagenta", imageWithBackground);
    }

    @Test
    public void testAddBackgroundWithSameColorFromAndToGivesFlatBackgroundColor() throws Exception {
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "YELLOW");
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, "YELLOW");
        BufferedImage baseImage = TestUtil.createBaseImage();
        BufferedImage imageWithBackground = background.addBackground(baseImage);
        TestUtil.writePngImageFile("DefaultBackground_flatBackgroundYellow", imageWithBackground);
    }

    @Test
    public void testAddBackgroundWithNullColorFromAndToGivesDiagonalBackgroundColorWithDefaultLightGrayAndWhite() throws Exception {
        BufferedImage baseImage = TestUtil.createBaseImage();
        BufferedImage imageWithBackground = background.addBackground(baseImage);
        TestUtil.writePngImageFile("DefaultBackground_gradientBackgroundLightGrayWhite", imageWithBackground);
    }
}
