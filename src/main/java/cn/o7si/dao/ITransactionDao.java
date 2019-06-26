package cn.o7si.dao;

/**
 * 交易相关操作的持久层接口
 */
public interface ITransactionDao {

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
    boolean addRecord(Integer accountId, Integer stockId, Integer walletId, Double price, Integer number, Double amount, int type);
}
