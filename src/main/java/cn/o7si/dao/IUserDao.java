package cn.o7si.dao;

import cn.o7si.entities.Account;

/**
 * 用户相关操作的持久层接口
 */
public interface IUserDao {

    /**
     * 通过账户名称查找账户
     * @param username  要查询的账户名称
     * @return          查询到的账户
     */
    Account findAccountByUsername(String username);

    /**
     * 创建（添加）账户
     * @param username  账户名称
     * @param password  账户密码
     * @return          如果创建成功，则返回true，否则返回false
     */
    boolean createAccount(String username, String password);

    /**
     * 根据账户名称和账户密码查找账户
     * @param username  账户名称
     * @param password  账户密码
     * @return          查找到的账户
     */
    Account findAccount(String username, String password);
}
