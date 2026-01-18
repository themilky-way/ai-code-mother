package com.qian.aicodemother.langgraph4j.tools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import com.qian.aicodemother.exception.BusinessException;
import com.qian.aicodemother.exception.ErrorCode;
import com.qian.aicodemother.langgraph4j.model.ImageResource;
import com.qian.aicodemother.langgraph4j.model.enums.ImageCategoryEnum;
import com.qian.aicodemother.manager.CosManager;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mermaid 架构图生成工具
 */

@Slf4j
@Component
public class MermaidDiagramTool {

    @Resource
    private CosManager cosManager;

    @Tool("将 Mermaid 代码转换为架构图图片，用于展示系统结构和技术关系")
    public List<ImageResource> generateMermaidDiagram(@P("Mermaid 图表代码") String mermaidCode,
                                                      @P("架构图描述") String description) {
        if (StrUtil.isBlank(mermaidCode)) {
            return new ArrayList<>();
        }
        try {
            // 转换为SVG图片
            File diagramFile = convertMermaidToSvg(mermaidCode);
            // 上传到COS
            String keyName = String.format("/mermaid/%s/%s",
                    RandomUtil.randomString(5), diagramFile.getName());
            String cosUrl = cosManager.uploadFile(keyName, diagramFile);
            // 清理临时文件
            FileUtil.del(diagramFile);
            if (StrUtil.isNotBlank(cosUrl)) {
                return Collections.singletonList(ImageResource.builder()
                        .category(ImageCategoryEnum.ARCHITECTURE)
                        .description(description)
                        .url(cosUrl)
                        .build());
            }
        } catch (Exception e) {
            log.error("生成架构图失败: {}", e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    /**
     * 将Mermaid代码转换为SVG图片
     */
    private File convertMermaidToSvg(String mermaidCode) {
        // 创建临时输入文件
        File tempInputFile = FileUtil.createTempFile("mermaid_input_", ".mmd", true);
        FileUtil.writeUtf8String(mermaidCode, tempInputFile);

        // 创建临时输出文件
        File tempOutputFile = FileUtil.createTempFile("mermaid_output_", ".svg", true);

        try {
            // 根据操作系统选择命令
            String command = SystemUtil.getOsInfo().isWindows() ? "mmdc.cmd" : "mmdc";

            // 使用 ProcessBuilder 构建命令，自动处理路径中的空格
            ProcessBuilder processBuilder = new ProcessBuilder(
                    command,
                    "-i", tempInputFile.getAbsolutePath(),
                    "-o", tempOutputFile.getAbsolutePath(),
                    "-b", "transparent"
            );

            // 合并错误流和标准输出流，方便调试
            processBuilder.redirectErrorStream(true);

            // 启动进程
            Process process = processBuilder.start();

            // 读取输出日志（可选，用于调试）
            // String output = StreamUtil.read(process.getInputStream(), StandardCharsets.UTF_8);

            // 等待命令执行完成，并获取退出码
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                // 如果退出码不为0，说明执行失败
                // 这里可以读取 process.getInputStream() 来获取 mmdc 的具体错误信息
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,
                        "Mermaid CLI 执行失败，退出码: " + exitCode);
            }

            // 检查输出文件
            if (!tempOutputFile.exists() || tempOutputFile.length() == 0) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,
                        "Mermaid CLI 执行成功但未生成有效文件");
            }
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "调用 Mermaid CLI 失败，请检查是否已安装 Node.js 和 mermaid-cli: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "Mermaid 渲染过程被中断: " + e.getMessage());
        } finally {
            // 无论成功失败，都删除输入文件
            FileUtil.del(tempInputFile);
        }

        return tempOutputFile;
    }
}


