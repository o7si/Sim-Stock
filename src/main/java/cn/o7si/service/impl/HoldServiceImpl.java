package cn.o7si.service.impl;

import cn.o7si.dao.IHoldDao;
import cn.o7si.entities.Hold;
import cn.o7si.service.IHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股票持有情况的业务层实现类
 */
@Service("holdServiceImpl")
public class HoldServiceImpl implements IHoldService {

    @Autowired
    private IHoldDao holdDao;

    /**
     * 根据账户ID获取股票持有情况列表
     *
     * @param accountId 账户ID
     * @return 股票持有情况列表
     */
    @Override
    public List<Hold> findHoldList(Integer accountId) {
        List<Hold> holds = holdDao.findHoldListByUaid(accountId);
        return holds;
    }

    /**
     * 查询账户对某支股票的持有情况
     *
     * @param stockId   股票编号
     * @param accountId 账户编号
     * @return 某支股票的持有情况
     */
    @Override
    public Hold findHold(Integer stockId, Integer accountId) {
        Hold hold = holdDao.findHold(stockId, accountId);
        return hold;
    }
}
