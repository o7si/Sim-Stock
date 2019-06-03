package cn.o7si.controller;

import cn.o7si.entities.User;
import cn.o7si.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 用户相关操作
 * 1. 验证用户名是否存在
 * 2. 用户注册
 * 3. 用户登录
 */
@Controller("userController")
public class UserController {

    @Autowired
    private IUserService userService;

    public void existUser(String username) {
        boolean isExist = userService.isExist(username);
        if (isExist) {
            System.out.println(username + "已被注册");
        } else {
            System.out.println(username + "未被注册");
        }
    }

    public void registerUser(String username, String password) {
        User rtUser = userService.register(username, password);
        if (rtUser == null) {
            System.out.println(username + "注册失败");
        } else {
            System.out.println(username + "注册成功");
        }
    }

    public void loginUser(User user) {
        User rtUser = userService.login(user);
        if (rtUser == null) {
            System.out.println(user + "登录成功");
        } else {
            System.out.println(user + "登录失败");
        }
    }
}
