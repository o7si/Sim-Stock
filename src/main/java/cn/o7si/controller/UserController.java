package cn.o7si.controller;

import cn.o7si.entities.User;
import cn.o7si.service.IUserService;
import cn.o7si.vo.ResponseData;
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
    public @ResponseBody ResponseData<User> existUser(@RequestBody User user) {
        String username = user.getUsername();
        boolean isExist = userService.isExist(username);

        ResponseData<User> rtData = new ResponseData<>();
        rtData.setOperate("isExist");
        rtData.setData(user);

        if (!isExist) {
            rtData.setStatusCode(1000);
            rtData.setDesc("INFO: 用户名[" + username + "]可被注册");
        } else {
            rtData.setStatusCode(1001);
            rtData.setDesc("INFO: 用户名[" + username + "]不可被注册");
        }
        return rtData;
    }

    @RequestMapping(value = "/register")
    public @ResponseBody ResponseData<User> registerUser(@RequestBody User user) {
        System.out.println("registerUser(String username, String password)");
        String username = user.getUsername();
        String password = user.getPassword();
        User rtUser = userService.register(username, password);

        ResponseData<User> rtData = new ResponseData<>();
        rtData.setOperate("register");
        rtData.setData(rtUser);
        if (rtUser != null) {
            rtData.setStatusCode(1003);
            rtData.setDesc("INFO: 用户[" + rtUser.getUsername() + "]注册成功");
        } else {
            rtData.setStatusCode(1004);
            rtData.setDesc("INFO: 用户[" + user.getUsername() + "]注册失败");
        }
        return rtData;
    }

    @RequestMapping(value = "/login")
    public @ResponseBody ResponseData<User> loginUser(@RequestBody User user) {
        User rtUser = userService.login(user);

        ResponseData<User> rtData = new ResponseData<>();
        rtData.setOperate("login");
        rtData.setData(rtUser);
        if (rtUser != null) {
            rtData.setStatusCode(1005);
            rtData.setDesc("INFO: 用户[" + rtUser.getUsername() + "]登录成功");
            System.out.println("登录成功");
        } else {
            rtData.setStatusCode(1006);
            rtData.setDesc("INFO: 用户[" + user.getUsername() + "]登录失败");
        }
        return rtData;
    }
}
