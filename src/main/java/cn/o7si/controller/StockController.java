package cn.o7si.controller;

import cn.o7si.entities.Stock;
import cn.o7si.service.IStockService;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.vo.PageBeanVo;
import cn.o7si.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

// 股票相关操作控制层
@RequestMapping("/stock")
@Controller("stockController")
public class StockController {

    @Autowired
    private IStockService stockService;

    // 功能：查询股票
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
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
