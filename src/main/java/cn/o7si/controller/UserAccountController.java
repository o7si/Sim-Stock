package cn.o7si.controller;

import cn.o7si.entities.Account;
import cn.o7si.service.IUserAccountService;
import cn.o7si.utils.JwtUtils;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.utils.TextUtils;
import cn.o7si.utils.VerifyUtils;
import cn.o7si.vo.ResponseData;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.soap.Text;
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
            rtData.put("username", username);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTEXIST);
            rtData.setDesc("服务器存在名称为[" + username + "]的用户");
        } else {
            // 不存在
            rtData.put("username", username);
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

        // 验证账户名称和登录密码的合法性
        if (!VerifyUtils.usernameVerify(username) || !VerifyUtils.loginPasswordVerify(password))
            return new ResponseData(StatusCodeUtils.USERNAMEORPASSWORDNOTLEGAL, "账户名称或登录密码不符合规范");

        // 调用业务层进行账户注册
        boolean registerRes = userAccountService.register(username, password);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (registerRes) {
            // 注册成功
            rtData.put("username", username);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTREGISTERSUCCESS);
            rtData.setDesc("账户[" + username + "]注册成功");
        } else {
            // 注册失败
            rtData.put("username", username);
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
            rtData.put("token", JwtUtils.createToken(rtAccount.getId()));
            rtAccount.eraseSensitiveData();
            rtData.put("account", rtAccount);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTLOGINSUCCESS);
            rtData.setDesc("账户[" + username + "]登录成功");
        } else {
            // 登录失败
            rtData.put("username", username);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTLOGINFAILURE);
            rtData.setDesc("账户[" + username + "]登录失败");
        }

        return rtData;
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData resetPassword(@RequestBody Map<String, Object> data) {
        // 判断用户登录状态
        Map<String, Claim> claim = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claim != null ? claim.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN, "未登录");

        // 获取相关数据Repeat
        String oldPassword = (String) data.get("oldPassword");
        String newPassword = (String) data.get("newPassword");
        String repeatPassword = (String) data.get("repeatPassword");

        // 提供参数不足
        if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(repeatPassword))
            return new ResponseData(StatusCodeUtils.MISSPARAM, "提供参数不足");

        // 如果新密码和重复密码不一致，则无法更改
        if (!newPassword.equals(repeatPassword))
            return new ResponseData(StatusCodeUtils.RESETPASSWORDFAILURE, "密码修改失败");

        boolean verify = userAccountService.verifyAccountPassword(accountId, oldPassword);

        // 验证失败
        if (!verify)
            return new ResponseData(StatusCodeUtils.RESETPASSWORDFAILURE, "密码修改失败");

        // 密码修改
        boolean rtValue = userAccountService.resetPassword(accountId, newPassword);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (rtValue) {
            // 修改成功
            rtData.setStatusCode(StatusCodeUtils.RESETPASSWORDSUCCESS);
            rtData.setDesc("密码修改成功");
        } else {
            // 修改失败
            rtData.setStatusCode(StatusCodeUtils.RESETPASSWORDFAILURE);
            rtData.setDesc("密码修改失败");
        }

        return rtData;
    }
}
