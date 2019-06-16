package cn.o7si.service.impl;

import cn.o7si.dao.IUserAccountDao;
import cn.o7si.entities.Account;
import cn.o7si.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAccountService")
public class UserAccountServiceImpl implements IUserAccountService {

    @Autowired
    private IUserAccountDao userAccountDao;

    @Override
    public boolean exist(String username) {
        Account rtAccount = userAccountDao.findAccountByUsername(username);
        return rtAccount != null;
    }

    @Override
    public boolean register(String username, String password) {
        boolean rtValue = userAccountDao.createAccount(username, password);
        return rtValue;
    }

    @Override
    public Account login(String username, String password) {
        Account rtAccount = userAccountDao.findAccountByUsernameAndPassword(username, password);
        return rtAccount;
    }
}
