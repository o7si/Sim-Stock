package cn.o7si.service;

import cn.o7si.entities.User;

/**
 * 用户相关操作业务层接口
 */
public interface IUserService {

    /**
     * 判断该用户名是否已被注册
     * @param username  用作查询的用户名
     * @return          是否被注册
     */
    boolean isExist(String username);

    /**
     * 用户注册
     * @param username  用户名
     * @param password  密码
     * @return          用户
     */
    User register(String username, String password);

    /**
     * 用户登录
     * @param user  将要登录的用户
     * @return      用户
     */
    User login(User user);
}
