package cn.o7si.dao.impl;

import cn.o7si.dao.IHoldDao;
import cn.o7si.entities.Hold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 股票持有情况的持久层实现类
 */
@Repository("holdDaoImpl")
public class HoldDaoImpl implements IHoldDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 增加股票持有信息
     * @param accountId 用户编号
     * @param walletId  钱包编号
     * @param stockId   股票编号
     * @param number    变化数量
     */
    @Override
    public void addHold(Integer accountId, Integer walletId, Integer stockId, Integer number) {
        // 插入数据SQL语句
        String sql = "insert into hold(uaid, wid, sid, number) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, accountId, walletId, stockId, number);
    }

    /**
     * 查询单支股票持有情况
     * @param accountId     账户编号
     * @param walletId      钱包编号
     * @param stockId       股票编号
     * @return              查询结果
     */
    @Override
    public Hold findHold(Integer accountId, Integer walletId, Integer stockId) {
        // 查询数据SQL语句
        String sql = "select * from hold where uaid=? and wid=? and sid=?";

        List<Hold> holds = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Hold.class), accountId, walletId, stockId);

        // 如果未查询到结果
        if (holds == null || holds.size() == 0)
            return null;

        // 如果结果集不唯一（按照设计不会出现此情况，此处只是为了增强代码的健壮性）
        if (holds.size() > 1)
            return null;

        // 返回查询结果
        return holds.get(0);
    }

    /**
     * 更新股票持有情况
     * @param hold          股票持有情况
     */
    @Override
    public void updateHold(Hold hold) {
        // 更新数据SQL语句
        String sql = "update hold set number=? where id=?";

        jdbcTemplate.update(sql, hold.getNumber(), hold.getId());
    }

    /**
     * 根据账户ID获取股票持有情况列表
     *
     * @param accountId 账户ID
     * @return 股票持有情况列表
     */
    @Override
    public List<Hold> findHoldListByUaid(Integer accountId) {
        // 查询数据SQL语句
        String sql = "select * from hold where uaid=? and number > 0";

        // 返回查询结果
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Hold.class), accountId);
    }
}