package cn.o7si.service.impl;

import cn.o7si.dao.ITransactionDao;
import cn.o7si.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 交易相关操作的业务层实现类
 */
@Service("transactionServiceImpl")
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionDao transactionDao;
}
