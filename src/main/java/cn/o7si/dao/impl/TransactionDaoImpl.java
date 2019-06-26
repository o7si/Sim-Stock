package cn.o7si.dao.impl;

import cn.o7si.dao.ITransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 交易相关操作的持久层实现类
 */

@Repository("transactionDaoImpl")
public class TransactionDaoImpl implements ITransactionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 增加交易记录
     * @param accountId     帐户编号
     * @param stockId       股票编号
     * @param walletId      钱包编号
     * @param price         单股售价
     * @param number        交易数量
     * @param amount        交易额
     * @param type          交易类型（购入，售出）
     * @return              如果添加成功则返回true，否则返回false
     */
    @Override
    public boolean addRecord(Integer accountId, Integer stockId, Integer walletId, Double price, Integer number, Double amount, int type) {
        // 增加交易记录时要使用的SQL语句
        String sql = "insert into transaction(number, single, turnover, type, uaid, wid, sid) values (?, ?, ?, ?, ?, ?, ?)";

        int rtValue = 0;
        try {
            // 执行SQL语句
            rtValue = jdbcTemplate.update(sql, number, price, amount, type, accountId, walletId, stockId);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回添加结果
        return rtValue == 1;
    }
}
