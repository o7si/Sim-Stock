package cn.o7si.service;

/**
 * 交易相关操作的业务层接口
 */
public interface ITransactionService {

    /**
     * 购买股票
     * @param accountId     用户编号
     * @param stockId       股票编号
     * @param number        购买数量
     * @return              如果交易成功则返回true，否则返回false
     */
    boolean buy(Integer accountId, Integer stockId, Integer number);
}