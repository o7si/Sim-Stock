package cn.o7si.service.impl;

import cn.o7si.dao.IHoldDao;
import cn.o7si.dao.IStockDao;
import cn.o7si.dao.ITransactionDao;
import cn.o7si.dao.IWalletDao;
import cn.o7si.entities.Hold;
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

    @Autowired
    private IHoldDao holdDao;

    /**
     * 购买股票
     * @param accountId 用户编号
     * @param stockId   股票编号
     * @param number    购买数量
     * @param password  交易密码
     */
    @Override
    public void buy(Integer accountId, Integer stockId, Integer number, Integer password) {

        // 根据AccountId获取钱包信息
        Wallet wallet = walletDao.findWalletByUaid(accountId);
        // 根据StockId获取股票信息
        Stock stock = stockDao.findStockById(stockId);

        // 未开户（无钱包）
        if (wallet == null)
            throw new RuntimeException("未开户");

        // 无该股票
        if (stock == null)
            throw new RuntimeException("无该股票");

        // 钱包余额
        Double balance = wallet.getBalance();
        // 单股售价
        Double price = stock.getPrice();
        // 交易额
        Double amount = price * number;

        // 核对交易密码
        if (!wallet.getPassword().equals(password))
            throw new RuntimeException("交易密码错误");

        // 如果余额不足, 中断交易
        if (amount > balance)
            throw new RuntimeException("用户钱包余额不足");

        // 股票数量不足，中断交易
        if (number > stock.getHold())
            throw new RuntimeException("股票剩余数量不足");

        // 1. 从钱包内扣除对应金额
        wallet.setBalance(balance - amount);
        walletDao.updateWallet(wallet);

        // 2. 修改股票相关信息
        stock.setSold(stock.getSold() + number);
        stock.setHold(stock.getHold() - number);
        stockDao.updateStock(stock);

        // 3. 更新用户股票持有信息
        // 3.1 查询是否已经有该支股票持有信息
        Hold hold = holdDao.findHold(accountId, wallet.getId(), stockId);
        // 3.2
        if (hold == null) {
            // 如果未持有该股票则进行添加
            holdDao.addHold(accountId, wallet.getId(), stockId, number);
        } else {
            // 如果已持有该股票则进行修改
            hold.setNumber(hold.getNumber() + number);
            holdDao.updateHold(hold);
        }

        // 4. 向交易表中插入此次交易记录
        transactionDao.addRecord(accountId, stockId, wallet.getId(), price, number, amount, 0);
    }
}
