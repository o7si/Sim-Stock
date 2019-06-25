package cn.o7si.controller;

import cn.o7si.entities.Wallet;
import cn.o7si.service.IWalletService;
import cn.o7si.utils.JwtUtils;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.vo.ResponseData;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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

    @Autowired
    private IWalletService walletService;

    @RequestMapping(value = "/openState", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResponseData openState(@RequestBody Map<String, Object> data) {
        // 获取用户登录状态
        Map<String, Claim> claims = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claims != null ? claims.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN, "账户未登录");

        // 调用业务层判断账户是否开户
        boolean openState = walletService.findOpenState(accountId);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (openState) {
            // 已经开户
            rtData.setStatusCode(StatusCodeUtils.OPENSTATEISTRUE);
            rtData.setDesc("该用户已经开户");
        } else {
            // 未开户
            rtData.setStatusCode(StatusCodeUtils.OPENSTATEISFALSE);
            rtData.setDesc("该用户尚未开户");
        }

        return rtData;
    }

    @RequestMapping(value = "/open", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResponseData open(@RequestBody Map<String, Object> data) {
        // 获取用户登录状态
        Map<String, Claim> claims = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claims != null ? claims.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN, "账户未登录");

        // 进行开户
        boolean rtValue = walletService.open(accountId);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (rtValue) {
            // 开户成功
            rtData.setStatusCode(StatusCodeUtils.OPENWALLETSUCCESS);
            rtData.setDesc("开户成功");
        } else {
            // 开户失败
            rtData.setStatusCode(StatusCodeUtils.OPENWALLETFAILURE);
            rtData.setDesc("开户失败");
        }

        return rtData;
    }

    @RequestMapping(value = "/getData", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResponseData getData(@RequestBody Map<String, Object> data) {
        // 获取用户登录状态
        Map<String, Claim> calims = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = calims != null ? calims.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN, "账户未登录");

        // 调用业务层进行查询
        Wallet wallet = walletService.findWallet(accountId);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (wallet != null) {
            // 查询成功
            wallet.eraseSensitiveData();
            rtData.put("wallet", wallet);
            rtData.setStatusCode(StatusCodeUtils.GETWALLETSUCCESS);
            rtData.setDesc("获取钱包信息成功");
        } else {
            // 查询失败
            rtData.setStatusCode(StatusCodeUtils.GETWALLETFAILURE);
            rtData.setDesc("获取钱包信息失败");
        }

        return rtData;
    }
}
