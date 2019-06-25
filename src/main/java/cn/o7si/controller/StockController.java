package cn.o7si.controller;

import cn.o7si.entities.Stock;
import cn.o7si.service.IStockService;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.vo.PageBeanVo;
import cn.o7si.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

// 股票相关操作控制层
@RequestMapping("/stock")
@Controller("stockController")
public class StockController {

    @Autowired
    private IStockService stockService;

    // 功能：查询股票信息
    @RequestMapping(value = "/getSingleData", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResponseData getData(@RequestBody Map<String, Object> data) {
        // 获取相关数据
        Integer stockId = (Integer) data.get("stockId");

        // 提供参数不足
        if (stockId == null)
            return new ResponseData(StatusCodeUtils.MISSPARAM, "参数不足");

        // 调用业务层查询单支股票信息
        Stock stock = stockService.findStock(stockId);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (stock != null) {
            // 查询成功
            rtData.put("stock", stock);
            rtData.setStatusCode(StatusCodeUtils.GETSINGLESTOCKSUCCESS);
            rtData.setDesc("获取单支股票信息成功");
        } else {
            // 查询失败
            rtData.setStatusCode(StatusCodeUtils.GETSINGLESTOCKFAILURE);
            rtData.setDesc("获取单只股票信息失败");
        }

        return rtData;
    }

    // 功能：查询股票
    @RequestMapping(value = "/getList", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResponseData getList(PageBeanVo page) {
        // 调用业务层进行查询
        PageBeanVo<Stock> rtPage = stockService.findList(page);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (rtPage.getData() != null && rtPage.getTotal() != 0) {
            // 成功查询到股票数据
            rtData.put("page", rtPage);
            rtData.setStatusCode(StatusCodeUtils.GETSTOCKLISTSUCCESS);
            rtData.setDesc("查询股票信息成功");
        } else {
            // 未查询到股票数据或查询过程中出现异常
            rtData.put("page", rtPage);
            rtData.setStatusCode(StatusCodeUtils.GETSTOCKLISTFAILURE);
            rtData.setDesc("查询股票信息失败");
        }

        return rtData;
    }
}
