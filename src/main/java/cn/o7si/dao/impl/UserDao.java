package cn.o7si.dao.impl;

import cn.o7si.dao.IUserDao;
import cn.o7si.entities.User;
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
    public User findUserByUsername(String username) {
        // 根据用户名查询用户
        List<User> users = jdbcTemplate.query("select * from user where username = ?", new BeanPropertyRowMapper<>(User.class), username);

        // 如果未查询到数据
        if (users == null || users.size() == 0)
            return null;

        // 如果结果集不唯一
        if (users.size() > 1)
            throw new RuntimeException();

        // 返回查询结果
        return users.get(0);
    }

    @Override
    public User findUser(String username, String password) {
        // 根据用户名和密码查询用户
        List<User> users = jdbcTemplate.query("select * from user where username = ? and password = ?", new BeanPropertyRowMapper<>(User.class), username, password);

        // 如果未查询到数据
        if (users == null || users.size() == 0)
            return null;

        // 如果结果集不唯一
        if (users.size() > 1)
            throw new RuntimeException();

        // 返回查询结果
        return null;
    }

    @Override
    public User findUser(User user) {
        return findUser(user.getUsername(), user.getPassword());
    }

    @Override
    public User saveUser(User user) {
        try {
            // 添加用户成功
            jdbcTemplate.update("insert into user (username, password) values (?, ?)", user.getUsername(), user.getPassword());
            return user;
        } catch (Exception e) {
            // 添加用户失败
            return null;
        }
    }
}
