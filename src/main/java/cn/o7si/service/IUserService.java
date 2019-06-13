package cn.o7si.service;

import cn.o7si.entities.Account;
import cn.o7si.entities.Information;

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
     * @param username  用作登录的账户账号
     * @param password  用作登录的账户密码
     * @return          如果登录成功则用户账户，否则返回null
     */
    Account login(String username, String password);

    /**
     * 查询个人信息
     * @param data      根据账户查询个人信息
     * @return          如果查询成功则返回true，否则返回false
     */
    Information find(Account data);

    /**
     * 修改个人信息
     * @param info      将要修改的个人信息
     * @return          如果修改成功则返回true，否则返回false
     */
    boolean modify(Information info);
}
