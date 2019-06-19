package cn.o7si.utils;

import org.omg.CORBA.INTERNAL;

/**
 * 全局状态码
 */
public class StatusCodeUtils {

    // 服务器内部未知错误
    public static final Integer UNKNOWERROR = -1;
    // 默认状态码
    public static final Integer DEFAULT = 0;
    // 缺失参数
    public static final Integer MISSPARAM = 1;

    // 用户未登录，无法操作
    public static final Integer NOTLOGGEDIN = 100;

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

    // 个人信息修改成功
    public static final Integer INFORMATIONMODIFYSUCCESS = 1013;
    // 个人信息修改失败
    public static final Integer INFORMATIONMODIFYFAILURE = 1014;
    // 错误的个人信息字段
    public static final Integer BADINFORMATIONFIELD = 1015;

    // 查询个人信息成功
    public static final Integer INFORMATIONFINDSUCCESS = 1017;
    // 查询个人信息失败
    public static final Integer INFORMATIONFINDFAILURE = 1018;

    // 头像上传成功
    public static final Integer AVATARMODIFYSUCCESS = 1021;
    // 头像上传失败
    public static final Integer AVATARMODIFYFAILURE = 1022;
    // 非图像文件
    public static final Integer FILENOTIMAGE = 1023;
}
