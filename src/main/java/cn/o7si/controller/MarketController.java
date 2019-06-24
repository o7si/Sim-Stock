package cn.o7si.controller;

import cn.o7si.entities.Market;
import cn.o7si.service.IMarketService;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/market")
@Controller("marketController")
public class MarketController {

    @Autowired
    private IMarketService marketService;

    @RequestMapping(value = "/getLateList", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResponseData getLateList(@RequestBody Map<String, Object> data) {
        // 提取数据（需要记录条数）
        Integer stockId = (Integer) data.get("stockId");
        Integer recordNumber = (Integer) data.get("recordNumber");

        if (stockId == null)
            return new ResponseData(StatusCodeUtils.MISSPARAM, "参数不足");

        // 查询最近一段时间的股票变化信息
        List<Market> markets = marketService.getLateList(recordNumber, stockId);

        // 设置响应给客户端的数据
        ResponseData rtData = new ResponseData();

        if (markets != null) {
            rtData.put("number", markets.size());
            rtData.put("markets", markets);
            rtData.setStatusCode(StatusCodeUtils.GETMARKETLATESUCCESS);
            rtData.setDesc("获取股票变化情况成功");
        } else {
            rtData.put("number", 0);
            rtData.put("markets", null);
            rtData.setStatusCode(StatusCodeUtils.GETMARKETLATEFAILURE);
            rtData.setDesc("获取股票变化情况失败");
        }

        return rtData;
    }
}