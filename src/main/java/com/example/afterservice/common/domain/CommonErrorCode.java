package com.example.afterservice.common.domain;


/**
 * 异常编码
 */
public enum CommonErrorCode implements ErrorCode {

    ////////////////////////////////////公用异常编码 //////////////////////////
    E_100101(100101,"传入参数与接口不匹配"),
    E_100102(100102,"验证码错误"),
    E_100103(100103,"验证码为空"),
    E_100104(100104,"查询结果为空"),
    E_100105(100105,"ID格式不正确或超出Long存储范围"),
    E_100106(100106,"上传错误"),
    E_100107(100107,"发送验证码错误"),
    E_100108(100108,"传入对象为空"),
    E_100109(100109,"手机号格式不正确"),
    E_100110(100110,"用户名为空"),
    E_100111(100111,"密码为空"),
    E_100112(100112,"手机号为空"),
    E_100113(100113,"邮箱已存在"),
    E_100114(100114,"用户名已存在"),
    E_100115(100115,"密码不正确"),
    E_100116(100116,"添加用户失败"),
    E_100117(100117,"对象已存在"),
    E_100118(100118,"插入数据失败"),
    E_100119(100119,"删除数据失败"),
    E_100120(100120,"更新数据失败"),

    CUSTOM(999998,"自定义异常"),
    /**
     * 未知错误
     */
    UNKNOWN(999999,"未知错误");


    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private CommonErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static CommonErrorCode setErrorCode(int code) {
        for (CommonErrorCode errorCode : CommonErrorCode.values()) {
            if (errorCode.getCode()==code) {
                return errorCode;
            }
        }
        return null;
    }
}
