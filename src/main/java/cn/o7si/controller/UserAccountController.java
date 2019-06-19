package cn.o7si.controller;

import cn.o7si.entities.Account;
import cn.o7si.service.IUserAccountService;
import cn.o7si.utils.JwtUtils;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.utils.TextUtils;
import cn.o7si.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


// 用户账户控制层
@RequestMapping("/user/account")
@Controller("userAccountController")
public class UserAccountController {

    @Autowired
    private IUserAccountService userAccountService;

    // 功能：账户是否已经存在
    @RequestMapping(value = "/exist", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData exist(@RequestBody Map<String, Object> data) {
        // 获取相关数据
        String username = (String) data.get("username");

        // 提供参数不足
        if (TextUtils.isEmpty(username))
            return new ResponseData(StatusCodeUtils.MISSPARAM);

        // 调用业务层判断该账户名称是否已经存在
        boolean exist = userAccountService.exist(username);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (exist) {
            // 存在
            rtData.setAction(null);
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTEXIST);
            rtData.setDesc("服务器存在名称为[" + username + "]的用户");
        } else {
            // 不存在
            rtData.setAction(null);
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTNOTEXIST);
            rtData.setDesc("服务器不存在名称为[" + username + "]的用户");
        }

        return rtData;
    }

    // 功能：账户注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData register(@RequestBody Map<String, Object> data) {
        // 获取相关数据
        String username = (String) data.get("username");
        String password = (String) data.get("password");

        // 提供参数不足
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
            return new ResponseData(StatusCodeUtils.MISSPARAM);

        // 调用业务层进行账户注册
        boolean registerRes = userAccountService.register(username, password);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (registerRes) {
            // 注册成功
            rtData.setAction(null);
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTREGISTERSUCCESS);
            rtData.setDesc("账户[" + username + "]注册成功");
        } else {
            // 注册失败
            rtData.setAction(null);
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTREGISTERFAILURE);
            rtData.setDesc("账户[" + username + "]注册失败");
        }

        return rtData;
    }

    // 功能：账户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData login(@RequestBody Map<String, Object> data) {
        // 获取相关数据
        String username = (String) data.get("username");
        String password = (String) data.get("password");

        // 提供参数不足
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
            return new ResponseData(StatusCodeUtils.MISSPARAM);

        // 调用业务层进行账户登录
        Account rtAccount = userAccountService.login(username, password);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (rtAccount != null) {
            // 登录成功
            rtData.setAction(null);
            // 生成Token
            rtData.put("token", JwtUtils.createToken(rtAccount.getId()));
            rtAccount.erase();
            rtData.put("account", rtAccount);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTLOGINSUCCESS);
            rtData.setDesc("账户[" + username + "]登录成功");
        } else {
            // 登录失败
            rtData.setAction(null);
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTLOGINFAILURE);
            rtData.setDesc("账户[" + username + "]登录失败");
        }

        return rtData;
    }
}
