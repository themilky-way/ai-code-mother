package com.qian.aicodemother.service.impl;

import com.qian.aicodemother.innerservice.InnerScreenshotService;
import com.qian.aicodemother.service.ScreenshotService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class InnerScreenshotServiceImpl implements InnerScreenshotService {

    @Resource
    private ScreenshotService screenshotService;

    @Override
    public String generateAndUploadScreenshot(String url) {
        return screenshotService.generateAndUploadScreenshot(url);

    }
}
