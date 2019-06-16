package cn.o7si.dao.impl;

import cn.o7si.dao.IUserInformationDao;
import cn.o7si.entities.Account;
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
    public boolean updateInformation(Information info) {
        // 更新详细信息时使用的SQL语句
        // String sql = "update user_information set avatar=?, nickname=?, birthday=?, place=?, hobby=?, phone=?, email=?, profile=? where uaid=?;";

        // 执行SQL语句
        // jdbcTemplate.update(sql, info.getAvatar());
        return false;
    }
}
