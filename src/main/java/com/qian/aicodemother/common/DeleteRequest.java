package com.qian.aicodemother.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 删除请求包装类
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * 删除id
     */
    private Long id;


    @Serial
    private static final long serialVersionUID = 1L;
}
