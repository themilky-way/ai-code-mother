package com.qian.aicodemother.ai;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AI服务创建工厂
 */
@Configuration
public class AiCodeGeneratorServiceFactory {

    @Resource
    ChatModel chatModel;

    @Resource
    StreamingChatModel streamingChatModel;

    /**
     * 创建 AI 代码生成器服务
     *
     */
    @Bean
    public AiCodeGeneratorService createAiCodeGeneratorService() {
        return AiServices.builder(AiCodeGeneratorService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .build();
    }


}
