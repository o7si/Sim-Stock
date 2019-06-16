package cn.o7si.dao.impl;

import cn.o7si.dao.IUserAccountDao;
import cn.o7si.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户账户持久层实现类
 */
@Repository("userAccountDao")
public class UserAccountDaoImpl implements IUserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
}
