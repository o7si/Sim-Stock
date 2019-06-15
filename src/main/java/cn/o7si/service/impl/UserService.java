package cn.o7si.service.impl;

import cn.o7si.dao.IUserDao;
import cn.o7si.entities.Account;
import cn.o7si.entities.Information;
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
        Account rtAccount = dao.findAccount(username, password);
        return rtAccount;
    }


    @Override
    public Information find(Account data) {
        // 确保账户存在
        Account account = dao.findAccountById(data.getId());

        // 如果账户不存在
        if (account == null)
            return null;

        // 如果账户存在，则查询个人信息
        Information rtInfo = dao.findInfomationByAccount(data);

        // 如果不存在该账户的个人信息数据
        if (rtInfo == null) {
            // 创建该账户的个人信息
            boolean rtValue = dao.createInformation(data);

            // 如果创建成功
            if (rtValue) {
                // 返回默认信息
                rtInfo = new Information();
                rtInfo.setUaid(data.getId());
                return rtInfo;
            }
            // 如果创建失败
            return null;
        }

        // 返回该账户的个人信息
        return rtInfo;
    }

    @Override
    public boolean modifyAvatar(String avatarName) {
        return false;
    }

    @Override
    public boolean modify(Information info) {
        boolean rtValue = dao.updateInformation(info);
        return rtValue;
    }
}
