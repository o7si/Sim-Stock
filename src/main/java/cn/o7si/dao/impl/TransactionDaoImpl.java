package cn.o7si.dao.impl;

import cn.o7si.dao.ITransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 交易相关操作的持久层实现类
 */

@Repository("transactionDaoImpl")
public class TransactionDaoImpl implements ITransactionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

}
