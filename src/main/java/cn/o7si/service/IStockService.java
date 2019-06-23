package cn.o7si.service;

import cn.o7si.entities.Stock;
import cn.o7si.vo.PageBeanVo;

import java.util.List;

public interface IStockService {

    PageBeanVo<Stock> findList(PageBeanVo page);
}
