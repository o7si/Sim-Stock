package cn.o7si.utils;

import org.junit.Test;

/**
 * 数据合法性验证
 */
public class VerifyUtils {

    /**
     * 验证账户名称的合法性
     * @param username  账户名称
     * @return          如果合法则返回true，否则返回false
     */
    public static boolean usernameVerify(String username) {
        // 判断用户名长度
        int minLength = 8;
        int maxLength = 18;
        if (username.length() < minLength || username.length() > maxLength)
            return false;

        // 数字数量
        int digit = 0;
        // 字母数量
        int letter = 0;
        // 空格数量
        int space = 0;

        for (int i = 0; i < username.length(); ++i) {


            if (Character.isDigit(username.charAt(i)))
                ++digit;

            if (Character.isLetter(username.charAt(i)))
                ++letter;

            if (Character.isWhitespace(username.charAt(i)))
                ++space;
        }

        return digit > 0 && letter > 0 && space == 0;
    }

    /**
     * 验证登录密码的合法性
     * @param password  登录密码
     * @return          如果合法则返回true，否则返回false
     */
    public static boolean loginPasswordVerify(String password) {
        int minLength = 6;
        int maxLength = 16;

        return password.length() >= minLength && password.length() <= maxLength;
    }
}
