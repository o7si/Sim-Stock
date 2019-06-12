package cn.o7si.service;

import cn.o7si.entities.Account;

/**
 * 用户相关操作的业务层接口
 */
public interface IUserService {

    /**
     * 判断该账户名称是否已经被使用
     * @param username  将要判断的账户名称
     * @return          如果已被使用则返回true，否则返回false
     */
    boolean exist(String username);

    /**
     * 用户注册
     * @param username  账户名称
     * @param password  账户密码
     * @return          如果注册成功则返回true，否则返回false
     */
    boolean register(String username, String password);

    /**
     * 用户登录
     * @param Account   用作登录的账户
     * @return          如果登录成功则返回true，否则返回false
     */
    /**
     * 用户登录
     * @param username  用作登录的账户账号
     * @param password  用作登录的账户密码
     * @return          如果登录成功返回
     */
    Account login(String username, String password);
}
