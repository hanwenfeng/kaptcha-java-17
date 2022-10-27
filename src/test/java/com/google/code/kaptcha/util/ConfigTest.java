package com.google.code.kaptcha.util;

import com.google.code.kaptcha.impl.DefaultBackground;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.impl.DefaultNoise;
import com.google.code.kaptcha.impl.FishEyeGimpy;
import com.google.code.kaptcha.impl.WaterRipple;
import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import com.google.code.kaptcha.text.impl.DefaultWordRenderer;
import com.google.code.kaptcha.text.impl.FiveLetterFirstNameTextCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author cliffano
 */
public class ConfigTest {
    private Config config;

    private Properties properties;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        config = new Config(properties);
    }

    @Test
    public void testIsBorderDrawnWithoutSpecifiedPropertyGivesDefaultValue() {
        assertTrue(config.isBorderDrawn());
    }

    @Test
    public void testIsBorderDrawnWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.border", "no");
        assertFalse(config.isBorderDrawn());
    }

    @Test
    public void testIsBorderDrawnWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.border", "Edwin POOLE");
        try {
            config.isBorderDrawn();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.border'. Value must be either yes or no.", ce.getMessage());
        }
    }

    @Test
    public void testGetBorderColorWithoutSpecifiedPropertyGivesDefaultValue() {
        Color borderColor = config.getBorderColor();
        assertEquals(0, borderColor.getRed());
        assertEquals(0, borderColor.getGreen());
        assertEquals(0, borderColor.getBlue());
    }

    @Test
    public void testGetBorderColorWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.border.color", "255,200,11");
        Color borderColor = config.getBorderColor();
        assertEquals(255, borderColor.getRed());
        assertEquals(200, borderColor.getGreen());
        assertEquals(11, borderColor.getBlue());
    }

    @Test
    public void testGetBorderColorWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.border.color", "Edwin POOLE");
        try {
            config.getBorderColor();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals(
                    "Invalid value 'Edwin POOLE' for config parameter 'kaptcha.border.color'.",
                    ce.getMessage());
        }
    }

    @Test
    public void testGetBorderThicknessWithoutSpecifiedPropertyGivesDefaultValue() {
        assertEquals(1, config.getBorderThickness());
    }

    @Test
    public void testGetBorderThicknessWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.border.thickness", "5");
        assertEquals(5, config.getBorderThickness());
    }

    @Test
    public void testGetBorderThicknessWithInvalidValueGivesExpectedValue() {
        properties.put("kaptcha.border.thickness", "Edwin POOLE");
        try {
            config.getBorderThickness();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals(
                    "Invalid value 'Edwin POOLE' for config parameter 'kaptcha.border.thickness'.",
                    ce.getMessage());
        }
    }

    @Test
    public void testGetProducerImplWithoutSpecifiedPropertyGivesDefaultValue() {
        assertTrue(config.getProducerImpl() instanceof DefaultKaptcha);
    }

    @Test
    public void testGetProducerImplWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.producer.impl",
                "com.google.code.kaptcha.impl.DefaultKaptcha");
        assertTrue(config.getProducerImpl() instanceof DefaultKaptcha);
    }

    @Test
    public void testGetProducerImplWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.producer.impl", "Edwin POOLE");
        try {
            config.getProducerImpl();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals(
                    "Invalid value 'Edwin POOLE' for config parameter 'kaptcha.producer.impl'.",
                    ce.getMessage());
        }
    }

    @Test
    public void testGetTextProducerImplWithoutSpecifiedPropertyGivesDefaultValue() {
        assertTrue(config.getTextProducerImpl() instanceof DefaultTextCreator);
    }

    @Test
    public void testGetTextProducerImplWithSpecifiedPropertyGivesExpectedValue() {
        properties
                .put("kaptcha.textproducer.impl",
                        "com.google.code.kaptcha.text.impl.FiveLetterFirstNameTextCreator");
        assertTrue(config.getTextProducerImpl() instanceof FiveLetterFirstNameTextCreator);
    }

    @Test
    public void testGetTextProducerImplWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.textproducer.impl", "Edwin POOLE");
        try {
            config.getTextProducerImpl();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals(
                    "Invalid value 'Edwin POOLE' for config parameter 'kaptcha.textproducer.impl'.",
                    ce.getMessage());
        }
    }

    @Test
    public void testGetTextProducerCharStringWithoutSpecifiedPropertyGivesDefaultValue() {
        assertEquals("abcde2345678gfynmnpwx", new String(config
                .getTextProducerCharString()));
    }

    @Test
    public void testGetTextProducerCharStringWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.textproducer.char.string", "xyzmno");
        assertEquals("xyzmno", new String(config.getTextProducerCharString()));
    }

    @Test
    public void testGetTextProducerCharLengthWithoutSpecifiedPropertyGivesDefaultValue() {
        assertEquals(5, config.getTextProducerCharLength());
    }

    @Test
    public void testGetTextProducerCharLengthWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.textproducer.char.length", "20");
        assertEquals(20, config.getTextProducerCharLength());
    }

    @Test
    public void testGetTextProducerCharLengthWithInvalidValueGivesExpectedValue() {
        properties.put("kaptcha.textproducer.char.length", "Edwin POOLE");
        try {
            config.getTextProducerCharLength();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.textproducer.char.length'.", ce.getMessage());
        }
    }

    @Test
    public void testGetTextProducerFontsWithoutSpecifiedPropertyGivesDefaultValue() {
        Font[] fonts = config.getTextProducerFonts(5);
        assertEquals(2, fonts.length);
        assertEquals("Arial", fonts[0].getFamily());
        assertTrue("Courier".equals(fonts[1].getFamily()) || "Monospaced".equals(fonts[1].getFamily()));
    }

    @Test
    public void testGetTextProducerFontsWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.textproducer.font.names", "Arial");
        Font[] fonts = config.getTextProducerFonts(5);
        assertEquals(1, fonts.length);
        assertEquals("Arial", fonts[0].getFamily());
    }

    @Test
    public void testGetTextProducerFontSizeWithoutSpecifiedPropertyGivesDefaultValue() {
        assertEquals(40, config.getTextProducerFontSize());
    }

    @Test
    public void testGetTextProducerFontSizeWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.textproducer.font.size", "80");
        assertEquals(80, config.getTextProducerFontSize());
    }

    @Test
    public void testGetTextProducerFontSizeWithInvalidValueGivesExpectedValue() {
        properties.put("kaptcha.textproducer.font.size", "Edwin POOLE");
        try {
            config.getTextProducerFontSize();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals(
                    "Invalid value 'Edwin POOLE' for config parameter 'kaptcha.textproducer.font.size'.",
                    ce.getMessage());
        }
    }

    @Test
    public void testGetTextProducerFontColorWithoutSpecifiedPropertyGivesDefaultValue() {
        Color borderColor = config.getTextProducerFontColor();
        assertEquals(0, borderColor.getRed());
        assertEquals(0, borderColor.getGreen());
        assertEquals(0, borderColor.getBlue());
    }

    @Test
    public void testGetTextProducerFontColorWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.textproducer.font.color", "215,100,11");
        Color borderColor = config.getTextProducerFontColor();
        assertEquals(215, borderColor.getRed());
        assertEquals(100, borderColor.getGreen());
        assertEquals(11, borderColor.getBlue());
    }

    @Test
    public void testGetTextProducerFontColorWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.textproducer.font.color", "Edwin POOLE");
        try {
            config.getTextProducerFontColor();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals(
                    "Invalid value 'Edwin POOLE' for config parameter 'kaptcha.textproducer.font.color'.",
                    ce.getMessage());
        }
    }

    @Test
    public void testGetNoiseImplWithoutSpecifiedPropertyGivesDefaultValue() {
        assertTrue(config.getNoiseImpl() instanceof DefaultNoise);
    }

    @Test
    public void testGetNoiseImplWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.noise.impl",
                "com.google.code.kaptcha.impl.DefaultNoise");
        assertTrue(config.getNoiseImpl() instanceof DefaultNoise);
    }

    @Test
    public void testGetNoiseImplWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.noise.impl", "Edwin POOLE");
        try {
            config.getNoiseImpl();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.noise.impl'.",
                    ce.getMessage());
        }
    }

    @Test
    public void testGetNoiseColorWithoutSpecifiedPropertyGivesDefaultValue() {
        Color borderColor = config.getNoiseColor();
        assertEquals(0, borderColor.getRed());
        assertEquals(0, borderColor.getGreen());
        assertEquals(0, borderColor.getBlue());
    }

    @Test
    public void testGetNoiseColorWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.noise.color", "115,120,111");
        Color borderColor = config.getNoiseColor();
        assertEquals(115, borderColor.getRed());
        assertEquals(120, borderColor.getGreen());
        assertEquals(111, borderColor.getBlue());
    }

    @Test
    public void testGetNoiseColorWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.noise.color", "Edwin POOLE");
        try {
            config.getNoiseColor();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.noise.color'.", ce.getMessage());
        }
    }

    @Test
    public void testGetObscurificatorImplWithoutSpecifiedPropertyGivesDefaultValue() {
        assertTrue(config.getObscurificatorImpl() instanceof WaterRipple);
    }

    @Test
    public void testGetObscurificatorImplWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.FishEyeGimpy");
        assertTrue(config.getObscurificatorImpl() instanceof FishEyeGimpy);
    }

    @Test
    public void testGetObscurificatorImplWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.obscurificator.impl", "Edwin POOLE");
        try {
            config.getObscurificatorImpl();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.obscurificator.impl'.", ce.getMessage());
        }
    }

    @Test
    public void testGetWordRendererImplWithoutSpecifiedPropertyGivesDefaultValue() {
        assertTrue(config.getWordRendererImpl() instanceof DefaultWordRenderer);
    }

    @Test
    public void testGetWordRendererImplWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        assertTrue(config.getWordRendererImpl() instanceof DefaultWordRenderer);
    }

    @Test
    public void testGetWordRendererImplWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.word.impl", "Edwin POOLE");
        try {
            config.getWordRendererImpl();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.word.impl'.", ce.getMessage());
        }
    }

    @Test
    public void testGetBackgroundImplWithoutSpecifiedPropertyGivesDefaultValue() {
        assertTrue(config.getBackgroundImpl() instanceof DefaultBackground);
    }

    @Test
    public void testGetBackgroundImplWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");
        assertTrue(config.getBackgroundImpl() instanceof DefaultBackground);
    }

    @Test
    public void testGetBackgroundImplWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.background.impl", "Edwin POOLE");
        try {
            config.getBackgroundImpl();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.background.impl'.", ce.getMessage());
        }
    }

    @Test
    public void testGetBackgroundColorFromWithoutSpecifiedPropertyGivesDefaultValue() {
        Color borderColor = config.getBackgroundColorFrom();
        assertEquals(192, borderColor.getRed());
        assertEquals(192, borderColor.getGreen());
        assertEquals(192, borderColor.getBlue());
    }

    @Test
    public void testGetBackgroundColorFromWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.background.clear.from", "115,120,111");
        Color borderColor = config.getBackgroundColorFrom();
        assertEquals(115, borderColor.getRed());
        assertEquals(120, borderColor.getGreen());
        assertEquals(111, borderColor.getBlue());
    }

    @Test
    public void testGetBackgroundColorFromWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.background.clear.from", "Edwin POOLE");
        try {
            config.getBackgroundColorFrom();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.background.clear.from'.", ce.getMessage());
        }
    }

    @Test
    public void testGetBackgroundColorToWithoutSpecifiedPropertyGivesDefaultValue() {
        Color borderColor = config.getBackgroundColorTo();
        assertEquals(255, borderColor.getRed());
        assertEquals(255, borderColor.getGreen());
        assertEquals(255, borderColor.getBlue());
    }

    @Test
    public void testGetBackgroundColorToWithSpecifiedPropertyGivesExpectedValue() {
        properties.put("kaptcha.background.clear.to", "115,120,111");
        Color borderColor = config.getBackgroundColorTo();
        assertEquals(115, borderColor.getRed());
        assertEquals(120, borderColor.getGreen());
        assertEquals(111, borderColor.getBlue());
    }

    @Test
    public void testGetBackgroundColorToWithInvalidValueGivesConfigException() {
        properties.put("kaptcha.background.clear.to", "Edwin POOLE");
        try {
            config.getBackgroundColorTo();
            fail("ConfigException should've been thrown.");
        } catch (ConfigException ce) {
            assertEquals("Invalid value 'Edwin POOLE' for config parameter 'kaptcha.background.clear.to'.", ce.getMessage());
        }
    }
}
