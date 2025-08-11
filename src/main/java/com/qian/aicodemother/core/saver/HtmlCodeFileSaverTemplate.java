package com.qian.aicodemother.core.saver;

import cn.hutool.core.util.StrUtil;
import com.qian.aicodemother.ai.model.HtmlCodeResult;
import com.qian.aicodemother.exception.BusinessException;
import com.qian.aicodemother.exception.ErrorCode;
import com.qian.aicodemother.model.enums.CodeGenTypeEnum;

/**
 * HTML 代码保存器
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult>{

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath,"index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        //HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码不能为空");
        }
    }
}
