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
}
