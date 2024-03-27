package com.yuge.ing.shardingsphere.core;

public enum ResultCode implements IErrorCode {
    /**
     * success
     */
    SUCCESS(0, "操作成功"),
    /**
     * failed
     */
    FAILED(99999, "内部运行异常！"),
    /**
     * call third failed
     */
    CALL_THIRD_FAILED(99991, "调用三方异常！"),
    /**
     * login failed
     */
    LOGIN_FAILED(10001, "用户名或密码错误！"),
    /**
     * login failed
     */
    ACCOUNT_DISABLED(10002, "账号禁用！"),
    /**
     * captcha failed
     */
    CAPTCHA_FAILED(10003, "验证码错误！"),
    /**
     * face failed
     */
    FACE_FAILED(10004, "该用户不存在！"),

    /**
     * validate failed
     */
    VALIDATE_FAILED(400, "参数检验失败"),
    /**
     * unauthorized
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * forbidden
     */
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
