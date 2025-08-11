package com.qian.aicodemother.core.saver;

import com.qian.aicodemother.ai.model.HtmlCodeResult;
import com.qian.aicodemother.ai.model.MultiFileCodeResult;
import com.qian.aicodemother.exception.BusinessException;
import com.qian.aicodemother.exception.ErrorCode;
import com.qian.aicodemother.model.enums.CodeGenTypeEnum;

import java.io.File;

/**
 * 代码文件保存执行器
 * 根据代码生成类型执行相应的保存逻辑
 */
public class CodeFileSaverExecutor {

    private static final  HtmlCodeFileSaverTemplate htmlCodeFileSaverTemplate = new HtmlCodeFileSaverTemplate();

    private static final MultiFileCodeFileSaverTemplate multiFileCodeFileSaverTemplate = new MultiFileCodeFileSaverTemplate();

    /**
     * 执行代码保存
     * @param codeResult 代码结果对象
     * @param codeGenTypeEnum 代码生成类型
     * @return 保存的目录
     */
    public static File executeSaver(Object codeResult, CodeGenTypeEnum codeGenTypeEnum) {
        return switch (codeGenTypeEnum) {
            case HTML -> htmlCodeFileSaverTemplate.saveCode((HtmlCodeResult)codeResult);
            case MULTI_FILE -> multiFileCodeFileSaverTemplate.saveCode((MultiFileCodeResult) codeResult);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型");
            };
        }

}
