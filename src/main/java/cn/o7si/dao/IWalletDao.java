package cn.o7si.dao;

import cn.o7si.entities.Wallet;

/**
 * 钱包相关操作的持久层接口
 */
public interface IWalletDao {

    /**
     * 为账户添加一个钱包
     * @param accountId     账户ID
     * @return              如果添加成功则返回true，否则返回false
     */
    boolean addWallet(Integer accountId);

    /**
     * 根据账户ID查询钱包
     * @param accountId     账户ID
     * @return              如果查询成功则返回钱包，否则返回null
     */
    Wallet findWalletByUaid(Integer accountId);

    /**
     * 根据账户ID查询钱包是否存在
     * @param accountId     账户ID
     * @return              如果查询成功则返回ture，否则返回false
     */
    boolean findExistByUaid(Integer accountId);

    /**
     * 更新钱包信息
     * @param wallet        钱包
     * @return              如果更新成功则返回true，否则返回false
     */
    boolean updateWallet(Wallet wallet) throws Exception;
}