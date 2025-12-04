package com.qian.aicodemother.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebScreenshotUtilsTest {

    @Test
    void saveWebPageScreenshot() {
        String webPageScreenshot = WebScreenshotUtils.saveWebPageScreenshot("https://www.baidu.com");
        String webPageScreenshot1 = WebScreenshotUtils.saveWebPageScreenshot("https://www.codefather.cn");
        Assertions.assertNotNull(webPageScreenshot);
        Assertions.assertNotNull(webPageScreenshot1);
    }
}