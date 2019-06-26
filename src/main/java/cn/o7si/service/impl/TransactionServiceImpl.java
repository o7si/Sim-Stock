package cn.o7si.service.impl;

import cn.o7si.dao.IStockDao;
import cn.o7si.dao.ITransactionDao;
import cn.o7si.dao.IWalletDao;
import cn.o7si.entities.Stock;
import cn.o7si.entities.Wallet;
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

    @Autowired
    private IWalletDao walletDao;

    @Autowired
    private IStockDao stockDao;

    /**
     * 购买股票
     * @param accountId 用户编号
     * @param stockId   股票编号
     * @param number    购买数量
     */
    @Override
    public void buy(Integer accountId, Integer stockId, Integer number) {

        // 根据AccountId获取钱包信息
        Wallet wallet = walletDao.findWalletByUaid(accountId);
        // 根据StockId获取股票信息
        Stock stock = stockDao.findStockById(stockId);

        // 钱包余额
        Double balance = wallet.getBalance();
        // 单股售价
        Double price = stock.getPrice();
        // 交易额
        Double amount = price * number;

        // 如果余额不足, 中断交易
        if (amount > balance)
            return;

        // 从钱包内扣除对应金额
        wallet.setBalance(balance - amount);
        walletDao.updateWallet(wallet);

        // 更新用户股票持有信息
        // TODO：暂空


        // 向交易表中插入此次交易记录
        transactionDao.addRecord(accountId, stockId, wallet.getId(), price, number, amount, 0);
    }
}
