package cn.o7si.service;

import cn.o7si.entities.Hold;

import java.util.List;

/**
 * 股票持有情况的业务层接口
 */
public interface IHoldService {

    /**
     * 根据账户ID获取股票持有情况列表
     *
     * @param accountId 账户ID
     * @return 股票持有情况列表
     */
    List<Hold> findHoldList(Integer accountId);

    /**
     * 查询账户对某支股票的持有情况
     *
     * @param stockId   股票编号
     * @param accountId 账户编号
     * @return 某支股票的持有情况
     */
    Hold findHold(Integer stockId, Integer accountId);
}
