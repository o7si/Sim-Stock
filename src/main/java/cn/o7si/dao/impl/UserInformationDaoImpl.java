package cn.o7si.dao.impl;

import cn.o7si.dao.IUserInformationDao;
import cn.o7si.entities.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息持久层实现类
 */
@Repository("userInformationDao")
public class UserInformationDaoImpl implements IUserInformationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean createInformation(Integer accountId) {
        // 向Information表中插入一条新的记录时使用的SQL语句
        String sql = "insert into user_information(uaid) values (?)";

        int rtValue = 0;
        try {
            // 执行SQL语句
            rtValue = jdbcTemplate.update(sql, accountId);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回结果
        return rtValue == 1;
    }

    @Override
    public Information findInformationByAccountId(Integer accountId) {
        // 根据账户ID查询详细信息时使用的SQL语句
        String sql = "select * from user_information where uaid=?";

        // 执行SQL语句
        List<Information> informationList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Information.class), accountId);

        // 如果未查询到结果
        if (informationList == null || informationList.size() == 0)
            return null;

        // 如果结果集不唯一
        if (informationList.size() > 1)
            return null;

        // 返回查询结果
        return informationList.get(0);
    }

    @Override
    public boolean updateOrdinaryInformation(String field, Object value, Integer accountId) {
        // 更新普通信息时使用的SQL语句
        String sql = "update user_information set " + field + "=? where uaid=?";

        int rtValue = 0;
        try {
            // 执行SQL语句
            rtValue = jdbcTemplate.update(sql, value, accountId);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回结果
        return rtValue == 1;
    }
}
