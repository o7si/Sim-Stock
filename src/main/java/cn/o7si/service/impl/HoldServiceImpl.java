package cn.o7si.service.impl;

import cn.o7si.dao.IHoldDao;
import cn.o7si.service.IHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 股票持有情况的业务层实现类
 */
@Service("holdServiceImpl")
public class HoldServiceImpl implements IHoldService {

    @Autowired
    private IHoldDao holdDao;
}
