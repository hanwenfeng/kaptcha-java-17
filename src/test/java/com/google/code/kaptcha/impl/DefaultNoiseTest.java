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
public class DefaultNoiseTest {
    private DefaultNoise noise;

    private Properties properties;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        noise = new DefaultNoise();
        noise.setConfig(new Config(properties));
    }

    @Test
    public void testMakeNoiseWithMultipleNoises() throws Exception {
        properties.put(Constants.KAPTCHA_NOISE_COLOR, "GREEN");
        BufferedImage image = TestUtil.createBaseImage();
        noise.makeNoise(image, .1f, .1f, .25f, .25f);
        noise.makeNoise(image, .5f, .5f, .5f, 5f);
        noise.makeNoise(image, 5f, .1f, 8f, .25f);
        TestUtil.writePngImageFile("DefaultNoise_multipleGreenNoisesDrawn",
                image);
    }

    @Test
    public void testMakeNoiseWithAllFactorsGreaterThan1fDoesntDrawAnything() throws Exception {
        properties.put(Constants.KAPTCHA_NOISE_COLOR, "RED");
        BufferedImage image = TestUtil.createBaseImage();
        noise.makeNoise(image, 1.1f, 1.1f, 1.1f, 1.1f);
        TestUtil.writePngImageFile("DefaultNoise_noNoiseDrawn", image);
    }

    @Test
    public void testMakeNoiseWithNullColorDefaultsToBlackNoises() throws Exception {
        BufferedImage image = TestUtil.createBaseImage();
        noise.makeNoise(image, .1f, .1f, .25f, .25f);
        noise.makeNoise(image, .5f, .5f, .5f, 5f);
        noise.makeNoise(image, 5f, .1f, 8f, .25f);
        TestUtil.writePngImageFile("DefaultNoise_multipleBlackNoisesDrawn", image);
    }
}
