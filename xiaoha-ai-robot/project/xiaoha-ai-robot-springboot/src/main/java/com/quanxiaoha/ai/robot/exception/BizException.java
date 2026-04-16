package com.quanxiaoha.ai.robot.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/16 13:48
 * @Version: v1.0.0
 * @Description: 业务异常
 */
@Getter
@Setter
public class BizException extends RuntimeException{
    //异常码
    private String errorCode;
    //错误码
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }

}
