package com.qian.aicodemother.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户注册请求
 */
@Data
public class UserRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}
