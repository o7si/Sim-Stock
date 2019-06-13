package cn.o7si.dao.impl;

import cn.o7si.dao.IUserDao;
import cn.o7si.entities.Account;
import cn.o7si.entities.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
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
        String sql = "select * from user_account where username = ?;";
        List<Account> accounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class), username);

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
    public Account findAccountById(Integer id) {
        // 根据账户ID查询账户
        String sql = "select * from user_account where id = ?";
        List<Account> accounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class), id);

        // 如果未查询到数据
        if (accounts == null || accounts.size() == 0)
            return null;

        // 如果结果集不唯一
        // id字段为主键，故此处只是为了增强程序的健壮性
        if (accounts.size() > 1)
            return null;

        // 返回查询结果
        return accounts.get(0);
    }

    @Override
    public boolean createAccount(String username, String password) {
        int rtValue = 0;
        try {
            // 向账户表中插入数据
            String sql = "insert into user_account(username, password) values (?, ?);";
            rtValue = jdbcTemplate.update(sql, username, password);
        } catch (Exception ignored) {
            // 忽略异常
        }
        // 返回结果
        return rtValue == 1;
    }

    @Override
    public Account findAccount(String username, String password) {
        // 根据账户名称和账户密码查询账户
        String sql = "select * from user_account where username = ? and password = ?;";
        List<Account> accounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class), username, password);

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
    public boolean createInformation(Account data) {
        int rtValue = 0;
        try {
            // 向用户信息表中插入数据
            String sql = "insert into user_information(uaid) values (?);";
            rtValue = jdbcTemplate.update(sql, data.getId());
        } catch (Exception ignored) {
            // 忽略异常
        }
        // 返回结果
        return rtValue == 1;
    }

    @Override
    public Information findInfomationByAccount(Account data) {
        // 根据账户id查询个人信息
        String sql = "select * from user_information where uaid = ?;";
        List<Information> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Information.class), data.getId());

        // 如果未查询到数据
        if (list == null || list.size() == 0)
            return null;

        // 如果结果集不唯一
        // 理论上不会出现该情况，故此处只是增强程序的健壮性
        if (list.size() > 1)
            return null;

        // 返回查询结果
        return list.get(0);
    }

    @Override
    public boolean updateInformation(Information info) {
        // 修改用户信息
        int rtValue = 0;
        try {
            // 获取所有信息
            Object args[] = new Object[]{
                    info.getAvatar(),
                    info.getNickname(),
                    info.getBirthday(),
                    info.getPlace(),
                    info.getHobby(),
                    info.getPhone(),
                    info.getEmail(),
                    info.getProfile(),
                    info.getUaid()
            };
            // 进行更新
            String sql = "update user_information set avatar=?, nickname=?, birthday=?, place=?, hobby=?, phone=?, email=?, profile=? where uaid=?;";
            rtValue = jdbcTemplate.update(sql, args);
        } catch (Exception ignored) {
            // 忽略异常
        }
        // 返回结果
        return rtValue == 1;
    }
}
