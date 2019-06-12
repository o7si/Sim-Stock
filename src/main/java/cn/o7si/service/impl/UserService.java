package cn.o7si.service.impl;

import cn.o7si.dao.IUserDao;
import cn.o7si.entities.Account;
import cn.o7si.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao dao;

    @Override
    public boolean exist(String username) {
        Account account = dao.findAccountByUsername(username);
        return account != null;
    }

    @Override
    public boolean register(String username, String password) {
        boolean rtValue = dao.createAccount(username, password);
        return rtValue;
    }

    @Override
    public Account login(String username, String password) {
        Account account = dao.findAccount(username, password);
        return account;
    }

}
