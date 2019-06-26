package cn.o7si.dao;

import cn.o7si.entities.Hold;

/**
 * 股票持有情况的持久层接口
 */
public interface IHoldDao {

    /**
     * 增加股票持有信息
     * @param accountId     帐户编号
     * @param walletId      钱包编号
     * @param stockId       股票编号
     * @param number        变化数量
     */
    void addHold(Integer accountId, Integer walletId, Integer stockId, Integer number);

    /**
     * 查询单支股票持有情况
     * @param accountId     账户编号
     * @param walletId      钱包编号
     * @param stockId       股票编号
     * @return              查询结果
     */
    Hold findHold(Integer accountId, Integer walletId, Integer stockId);

    /**
     * 更新股票持有情况
     * @param hold          股票持有情况
     */
    void updateHold(Hold hold);
}
