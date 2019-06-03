package cn.o7si.controller;

import cn.o7si.entities.User;
import cn.o7si.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关操作
 * 1. 验证用户名是否存在
 * 2. 用户注册
 * 3. 用户登录
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/isExist")
    public void existUser(@RequestBody User user) {
        System.out.println("existUser(String username)");
        String username = user.getUsername();
        boolean isExist = userService.isExist(username);
        if (isExist) {
            System.out.println(username + "已被注册");
        } else {
            System.out.println(username + "未被注册");
        }
    }

    @RequestMapping(value = "/register")
    public void registerUser(@RequestBody User user) {
        System.out.println("registerUser(String username, String password)");
        String username = user.getUsername();
        String password = user.getPassword();
        User rtUser = userService.register(username, password);
        if (rtUser == null) {
            System.out.println(username + "注册失败");
        } else {
            System.out.println(username + "注册成功");
        }
    }

    @RequestMapping(value = "/login")
    public @ResponseBody User loginUser(@RequestBody User user) {
        System.out.println("loginUser(User user)");
        User rtUser = userService.login(user);
        if (rtUser != null) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
        return rtUser;
    }
}
