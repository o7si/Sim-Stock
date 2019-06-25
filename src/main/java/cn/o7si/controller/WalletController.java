package cn.o7si.controller;

import cn.o7si.utils.JwtUtils;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.vo.ResponseData;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 钱包相关操作功能控制层
 */

@RequestMapping("/wallet")
@Controller("walletController")
public class WalletController {

    @RequestMapping(value = "/open", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResponseData open(@RequestBody Map<String, Object> data) {
        // 获取用户登录状态
        Map<String, Claim> claims = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claims != null ? claims.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN, "账户未登录");


        return new ResponseData(7777, "测试ing");
    }
}
