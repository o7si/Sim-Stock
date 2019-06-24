package cn.o7si.dao;

import cn.o7si.entities.Account;


// 用户账户持久层接口
public interface IUserAccountDao {

    /**
     * 添加账户
     * @param username  要添加的账户名称
     * @param password  要添加的账户密码
     * @return          如果添加成功则返回true，否则返回false
     */
    boolean createAccount(String username, String password);

    /**
     * 根据账户Id查询账户
     * @param id        用作查询的账户Id
     * @return          如果查询成功则返回该账户，否则返回null
     */
    Account findAccountById(Integer id);

    /**
     * 根据账户名称查询账户
     * @param username  用作查询的账户名称
     * @return          如果查询成功则返回该账户，否则返回null
     */
    Account findAccountByUsername(String username);

    /**
     * 根据账户名称和密码查询账户
     * @param username  用作查询的账户名称
     * @param password  用作查询的账户密码
     * @return          如果查询成功则返回该账户，否则返回null
     */
    Account findAccountByUsernameAndPassword(String username, String password);

    /**
     * 根据账户编号和密码查询账户
     * @param accountId 用作查询的账户编号
     * @param password  用作查询的账户密码
     * @return          如果查询成功则返回该账户，否则返回null
     */
    boolean findAccountByAccountIdAndPassword(Integer accountId, String password);

    /**
     * 修改账户信息
     * @param accountId     账户ID
     * @param field         将要修改的字段
     * @param newPassword   将要修改成为的值
     * @return              如果修改成功则返回true，否则返回false
     */
    boolean updateAccount(Integer accountId, String field, String newPassword);
}
