package cn.o7si.dao.impl;

import cn.o7si.dao.IUserAccountDao;
import cn.o7si.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// 用户账户持久层实现类
@Repository("userAccountDao")
public class UserAccountDaoImpl implements IUserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加账户
     * @param username  要添加的账户名称
     * @param password  要添加的账户密码
     * @return          如果添加成功则返回true，否则返回false
     */
    @Override
    public boolean createAccount(String username, String password) {
        // 创建账户时使用的SQL语句
        String sql = "insert into user_account(username, password) values (?, ?)";

        int rtValue = 0;
        try {
            // 执行SQL语句
            rtValue = jdbcTemplate.update(sql, username, password);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回结果
        return rtValue == 1;
    }

    /**
     * 根据账户Id查询账户
     * @param id        用作查询的账户Id
     * @return          如果查询成功则返回该账户，否则返回null
     */
    @Override
    public Account findAccountById(Integer id) {
        // 根据Id查询账户时使用的SQL语句
        String sql = "select * from user_account where id=?";

        // 执行SQL语句
        List<Account> accounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class), id);

        // 如果未查询到结果
        if (accounts == null || accounts.size() == 0)
            return null;

        // 如果结果集不唯一
        if (accounts.size() > 1)
            return null;

        // 返回查询结果
        return accounts.get(0);
    }

    /**
     * 根据账户名称查询账户
     * @param username  用作查询的账户名称
     * @return          如果查询成功则返回该账户，否则返回null
     */
    @Override
    public Account findAccountByUsername(String username) {
        // 根据Username查询账户时使用的SQL语句
        String sql = "select * from user_account where username=?";

        // 执行SQL语句
        List<Account> accounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class), username);

        // 如果未查询到结果
        if (accounts == null || accounts.size() == 0)
            return null;

        // 如果结果集不唯一
        if (accounts.size() > 1)
            return null;

        // 返回查询结果
        return accounts.get(0);
    }

    /**
     * 根据账户名称和密码查询账户
     * @param username  用作查询的账户名称
     * @param password  用作查询的账户密码
     * @return          如果查询成功则返回该账户，否则返回null
     */
    @Override
    public Account findAccountByUsernameAndPassword(String username, String password) {
        // 根据Username和Password查询账户时使用的SQL语句
        String sql = "select * from user_account where username=? and password=?";

        // 执行SQL语句
        List<Account> accounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class), username, password);

        // 如果未查询到结果
        if (accounts == null || accounts.size() == 0)
            return null;

        // 如果结果集不唯一
        if (accounts.size() > 1)
            return null;

        // 返回查询结果
        return accounts.get(0);
    }

    /**
     * 根据账户编号和密码查询账户
     * @param accountId 用作查询的账户编号
     * @param password  用作查询的账户密码
     * @return          如果查询成功则返回该账户，否则返回null
     */
    @Override
    public boolean findAccountByAccountIdAndPassword(Integer accountId, String password) {
        // 根据AccountId和Password查询账户时使用的SQL语句
        String sql = "select * from user_account where id=? and password=?";

        // 执行SQL语句
        List<Account> accounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class), accountId, password);

        // 如果未查询到结果
        if (accounts == null || accounts.size() == 0)
            return false;

        // 如果结果集不唯一
        if (accounts.size() > 1)
            return false;

        // 返回查询结果
        return true;
    }

    /**
     * 修改账户信息
     * @param accountId     账户ID
     * @param field         将要修改的字段
     * @param newPassword   将要修改成为的值
     * @return              如果修改成功则返回true，否则返回false
     */
    @Override
    public boolean updateAccount(Integer accountId, String field, String newPassword) {
        // 用作修改时使用的SQL语句
        String sql = "update user_account set " + field + "=? where id=?";

        int rtValue = 0;
        try {
            // 执行SQL语句
            rtValue = jdbcTemplate.update(sql, newPassword, accountId);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回结果
        return rtValue == 1;
    }
}
