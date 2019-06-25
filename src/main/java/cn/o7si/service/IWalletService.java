package cn.o7si.service;

import cn.o7si.entities.Wallet;

/**
 * 钱包相关操作的业务层接口
 */
public interface IWalletService {

    /**
     * 开户操作
     * @param accountId     进行开户操作的账户ID
     * @return              若开户成功则返回true，否则返回false
     */
    boolean open(Integer accountId);

    /**
     * 开户状态
     * @param accountId     查询开户状态的账户ID
     * @return              若已经开户则返回true，否则返回false
     */
    boolean findOpenState(Integer accountId);

    /**
     * 根据账户ID获取钱包信息
     * @param accountId     账户ID
     * @return              如果查询成功则返回钱包信息，否则返回null
     */
    Wallet findWallet(Integer accountId);
}
