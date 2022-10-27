package com.google.code.kaptcha.impl;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.util.Config;
import com.google.code.kaptcha.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * @author cliffano
 */
public class DefaultKaptchaTest {
    private DefaultKaptcha kaptcha;

    private Properties properties;

    @BeforeEach
    public void setUp() {
        kaptcha = new DefaultKaptcha();
        properties = new Properties();
        kaptcha.setConfig(new Config(properties));
    }

    @Test
    public void testCreateImageWithDefaultImpls() throws Exception {
        TestUtil.writePngImageFile("DefaultKaptcha_defaultImpls", kaptcha
                .createImage(kaptcha.createText()));
    }

    @Test
    public void testCreateImageWithConfiguredImpls() throws Exception {
        properties.put(Constants.KAPTCHA_BORDER, "no");
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "GREEN");
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, "MAGENTA");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "40");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "BLUE");
        properties.put(Constants.KAPTCHA_NOISE_COLOR, "RED");
        properties.put(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.put(Constants.KAPTCHA_WORDRENDERER_IMPL, "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_IMPL, "com.google.code.kaptcha.text.impl.ChineseTextProducer");
        TestUtil.writePngImageFile("DefaultKaptcha_configuredImpls", kaptcha.createImage("Fleet Street"));
    }

    @Test
    public void testCreateImageWithChineseText() throws Exception {
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_IMPL,
                "com.google.code.kaptcha.text.impl.ChineseTextProducer");
        String text = kaptcha.createText();
        TestUtil.writePngImageFile("DefaultKaptcha_chineseText", kaptcha
                .createImage(text));
    }
}
