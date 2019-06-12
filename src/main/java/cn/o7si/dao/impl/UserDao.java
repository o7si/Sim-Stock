package cn.o7si.dao.impl;

import cn.o7si.dao.IUserDao;
import cn.o7si.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户操作持久层实现类
 */
@Repository("userDao")
public class UserDao implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountByUsername(String username) {
        // 根据账户名称查询账户
        List<Account> accounts = jdbcTemplate.query("select * from user_account where username = ?", new BeanPropertyRowMapper<>(Account.class), username);

        // 如果未查询到数据
        if (accounts == null || accounts.size() == 0)
            return null;

        // 如果结果集不唯一
        // username字段设有唯一约束，故此处只是增强程序的健壮性
        if (accounts.size() > 1)
            return null;

        // 返回查询结果
        return accounts.get(0);
    }

    @Override
    public boolean createAccount(String username, String password) {
        int rtValue = 0;
        try {
            // 向表中插入数据
            rtValue = jdbcTemplate.update("insert into user_account(username, password) values (?, ?)", username, password);
        } catch (Exception ignored) {
            // 忽略异常
        }
        // 返回结果
        return rtValue == 1;
    }

    @Override
    public Account findAccount(String username, String password) {
        // 根据账户名称和账户密码查询账户
        List<Account> accounts = jdbcTemplate.query("select * from user_account where username = ? and password = ?", new BeanPropertyRowMapper<>(Account.class), username, password);

        // 如果未查询到数据
        if (accounts == null || accounts.size() == 0)
            return null;

        // 如果结果集不唯一
        // username字段设有唯一约束，故此处只是增强程序的健壮性
        if (accounts.size() > 1)
            return null;

        // 返回查询结果
        return accounts.get(0);
    }
}
