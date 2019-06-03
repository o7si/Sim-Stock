package cn.o7si.dao;

import cn.o7si.entities.User;

/**
 * 用户相关操作持久层接口
 */
public interface IUserDao {

    /**
     * 根据用户名查询用户
     * @param username  用作查询的用户名
     * @return          查找到的用户
     */
    User findUserByUsername(String username);

    /**
     * 根据用户名和密码查找用户
     * @param username  用作查询的用户名
     * @param password  用作查询用户密码
     * @return          查找到的用户
     */
    User findUser(String username, String password);

    /**
     * 查找用户
     * @param user  用于查找的用户
     * @return      查找到的用户
     */
    User findUser(User user);

    /**
     * 添加用户
     * @param user  将要添加的用户
     * @return      查找到的用户
     */
    User saveUser(User user);
}
