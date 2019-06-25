package cn.o7si.service.impl;

import cn.o7si.dao.IWalletDao;
import cn.o7si.entities.Wallet;
import cn.o7si.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 钱包相关操作的业务层实现类
 */

@Service("walletServiceImpl")
public class WalletServiceImpl implements IWalletService {

    @Autowired
    private IWalletDao walletDao;

    /**
     * 开户操作
     * @param accountId     进行开户操作的账户ID
     * @return              若开户成功则返回true，否则返回false
     */
    @Override
    public boolean open(Integer accountId) {
        // 判断用户是否已经开户
        boolean openState = walletDao.findExistByUaid(accountId);

        // 如果已经开户
        if (openState)
            return false;

        // 进行开户
        boolean rtValue = walletDao.addWallet(accountId);

        // 返回开户结果
        return rtValue;
    }

    /**
     * 开户状态
     * @param accountId     查询开户状态的账户ID
     * @return              若已经开户则返回true，否则返回false
     */
    @Override
    public boolean findOpenState(Integer accountId) {
        boolean rtValue = walletDao.findExistByUaid(accountId);
        return rtValue;
    }

    /**
     * 根据账户ID获取钱包信息
     * @param accountId     账户ID
     * @return              如果查询成功则返回钱包信息，否则返回null
     */
    @Override
    public Wallet findWallet(Integer accountId) {
        Wallet rtWallet = walletDao.findWalletByUaid(accountId);
        return rtWallet;
    }
}
