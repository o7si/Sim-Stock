package cn.o7si.controller;

import cn.o7si.entities.Account;
import cn.o7si.entities.Information;
import cn.o7si.service.IUserService;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @RequestMapping(value = "/account/exist", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData exist(@RequestBody Account data) {
        // 调用业务层判断该账户名称是否被使用
        String username = data.getUsername();
        boolean exist = service.exist(username);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (exist) {
            rtData.setAction("");
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTEXIST);
            rtData.setDesc("服务器存在名称为[" + data.getUsername() + "]的用户");
        } else {
            rtData.setAction("");
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTNOTEXIST);
            rtData.setDesc("服务器不存在名称为[" + data.getUsername() + "]的用户");
        }

        return rtData;
    }

    @RequestMapping(value = "/account/register", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData register(@RequestBody Account data) {
        // 调用业务层进行账户注册
        String username = data.getUsername();
        String password = data.getPassword();
        boolean registerRes = service.register(username, password);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (registerRes) {
            rtData.setAction("");
            rtData.addData("username", username);
            rtData.addData("password", password);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTREGISTERSUCCESS);
            rtData.setDesc("账户[" + username + "]注册成功");
        } else {
            rtData.setAction("");
            rtData.addData("username", username);
            rtData.addData("password", password);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTREGISTERFAILURE);
            rtData.setDesc("账户[" + username + "]注册失败");
        }

        return rtData;
    }

    @RequestMapping(value = "/account/login", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData login(@RequestBody Account data) {
        // 调用业务层进行账户登录
        String username = data.getUsername();
        String password = data.getPassword();
        Account account = service.login(username, password);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (account != null) {
            rtData.setAction("");
            rtData.addData("account", account);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTLOGINSUCCESS);
            rtData.setDesc("账户[" + username + "]登录成功");
        } else {
            rtData.setAction("");
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTLOGINFAILURE);
            rtData.setDesc("账户[" + username + "]登录失败");
        }

        return rtData;
    }

    @RequestMapping(value = "/information/show", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData show(@RequestBody Account data) {
        // 调用业务层查询个人信息
        Information info = service.find(data);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (info != null) {
            rtData.setAction("");
            rtData.addData("information", info);
            rtData.setStatusCode(StatusCodeUtils.INFORMATIONFINDSUCCESS);
            rtData.setDesc("查询个人信息成功");
        } else {
            rtData.setAction("");
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.INFORMATIONFINDFAILURE);
            rtData.setDesc("查询个人信息失败");
        }

        return rtData;
    }

    @RequestMapping(value = "/information/modify", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData modify(@RequestBody Information data) {
        // 调用业务层进行个人信息修改
        boolean success = service.modify(data);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (success) {
            rtData.setAction("");
            rtData.addData("information", data);
            rtData.setStatusCode(StatusCodeUtils.INFORMATIONMODIFYSUCCESS);
            rtData.setDesc("个人信息修改成功");
        } else {
            rtData.setAction("");
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.INFORMATIONMODIFYFAILURE);
            rtData.setDesc("个人信息修改失败");
        }

        return rtData;
    }
}
