package cn.o7si.controller;

import cn.o7si.entities.Hold;
import cn.o7si.service.IHoldService;
import cn.o7si.utils.JwtUtils;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.vo.ResponseData;
import com.auth0.jwt.interfaces.Claim;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 股票持有情况的控制层
 */
@RequestMapping("/hold")
@Controller("holdController")
public class HoldController {

    @Autowired
    private IHoldService holdService;

    /**
     * 功能：查询股票持有列表
     *
     * @param data 从前端接收的数据
     * @return 响应给前端的数据
     */
    @RequestMapping(value = "/getHoldList", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData getHoldList(@RequestBody Map<String, Object> data) {
        // 判断用户是否登录
        Map<String, Claim> claims = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claims != null ? claims.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN, "账户未登录");

        // 调用业务层查询股票持有列表
        try {
            // 未出现异常，查询成功
            List<Hold> holds = holdService.findHoldList(accountId);

            return new ResponseData(new HashMap<String, Object>() {
                {
                    put("holds", holds);
                }
            }, StatusCodeUtils.GETLISTHOLDSUCCESS, "股票持有列表查询成功");
        } catch (Exception e) {
            // 异常信息
            e.printStackTrace();
            // 出现异常，查询失败
            return new ResponseData(StatusCodeUtils.GETLISTHOLDFAILURE, "股票持有列表查询失败");
        }
    }
}
