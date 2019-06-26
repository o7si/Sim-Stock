package cn.o7si.dao.impl;

import cn.o7si.dao.IHoldDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 股票持有情况的持久层实现类
 */
@Repository("holdDaoImpl")
public class HoldDaoImpl implements IHoldDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
