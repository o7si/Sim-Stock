package cn.o7si.service.impl;

import cn.o7si.dao.IUserDao;
import cn.o7si.entities.User;
import cn.o7si.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public boolean isExist(String username) {
        // 根据用户名查询
        User user = userDao.findUserByUsername(username);
        // 如果未查询到用户，说明该用户名未被注册
        // 如果查询到该用户，说明该用户名已被注册
        return user != null;
    }

    @Override
    public User register(String username, String password) {
        // 创建将要注册的用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        // 进行注册并返回结果
        return userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        // 进行登录
        return userDao.findUser(user);
    }
}
