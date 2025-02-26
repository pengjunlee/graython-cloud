package com.pengjunlee.demo.common.biz;

import gray.bingo.common.inface.ExceptionInfo;
import lombok.Getter;

@Getter
public enum BIZExceptionCodeEnum implements ExceptionInfo {
    // 状态码 20000：成功，20001~20999:框架层异常
    // 自定义业务异常状态码取值范围：21000 ～ 29999
    AUTH_ERROR(false, 20002, "鉴权异常"),
    PARAM_ERROR(false, 20003, "参数异常"),
    RESULT_ERROR(false, 20004, "结果异常"),
    UNKNOWN_RES_TYPE_ERROR(false, 20005, "未知资源类型异常"),
    UNSUPPORTED_OPERATION_ERROR(false, 20006, "只读用户禁止操作"),
    RATE_LIMIT_ERROR(false, 20007, "单日流量超过限额"),
    USERNAME_PASSWORD_ERROR(false, 20008, "用户名或密码错误"),
    UNKNOWN_ERROR(false, 20099, "未知错误"),
    ;

    // 响应是否成功
    private final Boolean success;
    // 响应状态码
    private final Integer code;
    // 响应信息
    private final String message;

    BIZExceptionCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
