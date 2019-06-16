package cn.o7si.dao;

import cn.o7si.entities.Account;

/**
 * 用户账户持久层接口
 */
public interface IUserAccountDao {

    boolean createAccount(String username, String password);

    Account findAccountById(Integer id);

    Account findAccountByUsername(String username);

    Account findAccountByUsernameAndPassword(String username, String password);
}
