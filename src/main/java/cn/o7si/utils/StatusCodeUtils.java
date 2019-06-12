package cn.o7si.utils;

/**
 * 全局状态码
 */
public class StatusCodeUtils {

    // 默认状态码
    public static final Integer DEFAULT = 0;
    // 服务器内部未知错误
    public static final Integer UNKNOWERROR = -1;

    // 账户名称不存在
    public static final Integer ACCOUNTNOTEXIST = 1000;
    // 账户名称存在
    public static final Integer ACCOUNTEXIST = 1001;

    // 账户注册成功
    public static final Integer ACCOUNTREGISTERSUCCESS = 1004;
    // 账户注册失败
    public static final Integer ACCOUNTREGISTERFAILURE = 1005;

    // 账户登录成功
    public static final Integer ACCOUNTLOGINSUCCESS = 1009;
    // 账户登录失败
    public static final Integer ACCOUNTLOGINFAILURE = 1010;
}
