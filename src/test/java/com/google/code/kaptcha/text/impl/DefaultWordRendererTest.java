package com.google.code.kaptcha.text.impl;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.util.Config;
import com.google.code.kaptcha.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author cliffano
 */
public class DefaultWordRendererTest {
    private DefaultWordRenderer renderer;

    private Properties properties;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        renderer = new DefaultWordRenderer();
        renderer.setConfig(new Config(properties));
    }

    @Test
    public void testRenderWordGivesImageWithExpectedDimension() {
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "20");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "BLUE");
        BufferedImage image = renderer.renderWord("Edwin POOLE", 400, 300);
        assertEquals(400, image.getWidth());
        assertEquals(300, image.getHeight());
    }

    @Test
    public void testRenderWordWithMultipleFonts() throws Exception {
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "20");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES,
                "Arial,Helvetica");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "RED");
        BufferedImage image = renderer.renderWord("Shirley SCHMIDT", 300, 80);
        TestUtil.writePngImageFile("DefaultWordRenderer_redFontDrawn", image);
    }

    @Test
    public void testRenderWordWithWidthLessThanFontStartPosDoesntDrawAnyFont()
            throws Exception {
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "20");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "GREEN");
        BufferedImage image = renderer.renderWord("Edwin POOLE", 25, 80);
        TestUtil.writePngImageFile("DefaultWordRenderer_noFontDrawn", image);
    }

    @Test
    public void testRenderWordWithNullFontConfigGivesDefaultFonts()
            throws Exception {
        BufferedImage image = renderer.renderWord("Denny CRANE", 300, 80);
        TestUtil.writePngImageFile("DefaultWordRenderer_defaultBlackFontDrawn",
                image);
    }
}
