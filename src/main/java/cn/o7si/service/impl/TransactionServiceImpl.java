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
     * @param accountId     用户编号
     * @param stockId       股票编号
     * @param number        购买数量
     * @return              如果交易成功则返回true，否则返回false
     *
     * 未添加事务控制，则不能判断是否交易成功，暂时返回true
     */
    @Override
    public boolean buy(Integer accountId, Integer stockId, Integer number) {

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

        System.out.println("判断余额");

        // 如果余额不足, 中断交易
        if (amount > balance)
            return false;

        System.out.println("余额够");

        try {
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println("楚玲错误");
//            e.printStackTrace();
        }

        // 从钱包内扣除对应金额
        wallet.setBalance(balance - amount);
        try {
            walletDao.updateWallet(wallet);
        } catch (Exception e) {

        }

        // 更新用户股票持有信息
        // TODO：暂空


        // 向交易表中插入此次交易记录
        transactionDao.addRecord(accountId, stockId, wallet.getId(), price, number, amount, 0);

        return true;
    }
}
