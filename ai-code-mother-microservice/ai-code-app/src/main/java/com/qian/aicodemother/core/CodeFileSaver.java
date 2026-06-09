package com.qian.aicodemother.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.qian.aicodemother.ai.model.HtmlCodeResult;
import com.qian.aicodemother.ai.model.MultiFileCodeResult;
import com.qian.aicodemother.model.enums.CodeGenTypeEnum;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 文件保存器
 */
@Deprecated
public class CodeFileSaver {

    /**
     * 文件保存根路径
     */
    private static final String FILE_SAVE_ROOT_PATH = System.getProperty("user.dir") + "/temp/code_output/";

    /**
     * 保存 HTML 网页代码
     * @param htmlCodeResult HTML代码结构化输出结果
     * @return 保存的文件目录
     */
    public static File saveHtmlCodeResult(HtmlCodeResult htmlCodeResult) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.HTML.getValue());
        writeToFile(baseDirPath, "index.html", htmlCodeResult.getHtmlCode());
        return new File(baseDirPath);
    }

    /**
     * 保存多文件网页代码
     * @param multiFileCodeResult 多文件代码结构化输出结果
     * @return 保存的文件目录
     */
    public static File saveMultiFileCodeResult(MultiFileCodeResult multiFileCodeResult) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.MULTI_FILE.getValue());
        writeToFile(baseDirPath, "index.html", multiFileCodeResult.getHtmlCode());
        writeToFile(baseDirPath, "main.js", multiFileCodeResult.getJsCode());
        writeToFile(baseDirPath, "main.css", multiFileCodeResult.getCssCode());
        return new File(baseDirPath);
    }
    /**
     * 构建文件的唯一路径：temp/code_output/bizType_雪花ID
     * @param bizType 代码生成类型
     * @return 唯一路径
     */
    private static String buildUniqueDir(String bizType) {
        String uniqueDirName = StrUtil.format("{}_{}", bizType, IdUtil.getSnowflakeNextIdStr());
        String dirPath = FILE_SAVE_ROOT_PATH + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }

    /**
     * 保存单个文件
     */
    private static void writeToFile(String dirPath, String fileName, String content) {
        String filePath = dirPath + File.separator + fileName;
        FileUtil.writeString(content , filePath, StandardCharsets.UTF_8);

    }

}
