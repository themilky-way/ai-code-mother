package com.qian.aicodemother.ai;

import com.qian.aicodemother.ai.model.HtmlCodeResult;
import com.qian.aicodemother.ai.model.MultiFileCodeResult;
import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

public interface AiCodeGeneratorService {

    /**
     * 生成 HTML 代码
     *
     * @param userMassage 用户提示词
     * @return AI输出结果
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    HtmlCodeResult generateHtmlCode(String userMassage);

    /**
     * 生成多文件代码
     *
     * @param userMassage 用户提示词
     * @return AI输出结果
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    MultiFileCodeResult generateMultiFileCode(String userMassage);


    /**
     * 流式生成 HTML 代码
     *
     * @param userMassage 用户提示词
     * @return AI输出结果
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    Flux<String> generateHtmlCodeStream(String userMassage);

    /**
     * 流式生成多文件代码
     *
     * @param userMassage 用户提示词
     * @return AI输出结果
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    Flux<String> generateMultiFileCodeStream(String userMassage);

}
