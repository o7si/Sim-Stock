package cn.o7si.service.impl;

import cn.o7si.dao.IUserAccountDao;
import cn.o7si.entities.Account;
import cn.o7si.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 用户账户相关操作业务层实现类
@Service("userAccountService")
public class UserAccountServiceImpl implements IUserAccountService {

    @Autowired
    private IUserAccountDao userAccountDao;

    /**
     * 判断该账户名称是否已经被使用
     * @param username  将要判断的账户名称
     * @return          如果账户存在则返回true，否则返回false
     */
    @Override
    public boolean exist(String username) {
        Account rtAccount = userAccountDao.findAccountByUsername(username);
        return rtAccount != null;
    }

    /**
     * 用户注册
     * @param username  进行注册的账户名称
     * @param password  进行注册的账户密码
     * @return          如果账户注册成功则返回true，否则返回false
     */
    @Override
    public boolean register(String username, String password) {
        boolean rtValue = userAccountDao.createAccount(username, password);
        return rtValue;
    }

    /**
     * 用户登录
     * @param username  用作登录的账户账号
     * @param password  用作登录的账户密码
     * @return          如果账户登录成功则返回账户信息，否则返回null
     */
    @Override
    public Account login(String username, String password) {
        Account rtAccount = userAccountDao.findAccountByUsernameAndPassword(username, password);
        return rtAccount;
    }

    /**
     * 验证账户密码
     * @param accountId 账户ID
     * @param password  密码
     * @return          如果验证成功则返回true，否则返回false
     */
    @Override
    public boolean verifyAccountPassword(Integer accountId, String password) {
        boolean verify = userAccountDao.findAccountByAccountIdAndPassword(accountId, password);
        return verify;
    }

    /**
     * 密码修改
     * @param accountId     账户ID
     * @param newPassword   新密码
     * @return              如果密码修改成功则返回ture，否则返回false
     */
    @Override
    public boolean resetPassword(Integer accountId, String newPassword) {
        boolean rtValue = userAccountDao.updateAccount(accountId, "password", newPassword);
        return rtValue;
    }
}
