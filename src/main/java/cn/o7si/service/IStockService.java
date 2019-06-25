package cn.o7si.service;

import cn.o7si.entities.Stock;
import cn.o7si.vo.PageBeanVo;

import java.util.List;

public interface IStockService {

    PageBeanVo<Stock> findList(PageBeanVo page);

    /**
     * 根据ID查找股票信息
     * @param stockId   股票ID
     * @return          返回查找到的股票
     */
    Stock findStock(Integer stockId);
}
