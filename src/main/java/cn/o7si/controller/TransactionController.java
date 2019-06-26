package cn.o7si.controller;

import cn.o7si.service.ITransactionService;
import cn.o7si.utils.JwtUtils;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.utils.TextUtils;
import cn.o7si.vo.ResponseData;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 交易相关操作的控制层
 */
@RequestMapping("/transaction")
@Controller("transactionController")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    /**
     * 功能：购买股票
     * @param data  从前端接收的数据
     * @return      响应给前端的数据
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData buy(@RequestBody Map<String, Object> data) {
        // 判断用户是否登录
        Map<String, Claim> claims = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claims != null ? claims.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN, "账户未登录");

        // 获取相关数据
        Integer stockId = (Integer) data.get("stockId");
        Integer number = (Integer) data.get("number");
        Integer password = (Integer) data.get("password");

        // 如果参数不足
        if (stockId == null || number == null || password == null)
            return new ResponseData(StatusCodeUtils.MISSPARAM, "参数不足");

        // 如果参数不规范
        if (number <= 0)
            return new ResponseData(StatusCodeUtils.MUSTPOSITIVE, "交易量必须为正整数");

        // 调用业务层进行股票购买
        try {
            // 未出现异常，事务成功提交
            transactionService.buy(accountId, stockId, number, password);

            return new ResponseData(StatusCodeUtils.BUYSTOCKSUCCESS, "购入股票成功");
        } catch (Exception e) {
            // 异常信息
            e.printStackTrace();
            // 出现异常，事务已经回滚
            return new ResponseData(StatusCodeUtils.BUYSTOCKFAILURE, "购入股票失败");
        }
    }

    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData sell(@RequestBody Map<String, Object> data) {
        // 判断用户是否登录
        Map<String, Claim> claims = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claims != null ? claims.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN, "账户未登录");

        // 获取相关数据
        Integer stockId = (Integer) data.get("stockId");
        Integer number = (Integer) data.get("number");
        Integer password = (Integer) data.get("password");

        // 如果参数不足
        if (stockId == null || number == null || password == null)
            return new ResponseData(StatusCodeUtils.MISSPARAM, "参数不足");

        // 如果参数不规范
        if (number <= 0)
            return new ResponseData(StatusCodeUtils.MUSTPOSITIVE, "交易量必须为正整数");

        // 调用业务层进行股票售出
        try {
            // 未出现异常，事务成功提交
            transactionService.sell(accountId, stockId, number, password);

            return new ResponseData(1, "售出股票成功");
        } catch (Exception e) {
            // 异常信息
            e.printStackTrace();
            // 出现异常，事务已经回滚
            return new ResponseData(1, "售出股票失败");
        }
    }
}
