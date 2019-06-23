package cn.o7si.service.impl;

import cn.o7si.dao.IStockDao;
import cn.o7si.entities.Stock;
import cn.o7si.service.IStockService;
import cn.o7si.vo.PageBeanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stockServiceImpl")
public class StockServiceImpl implements IStockService {

    @Autowired
    private IStockDao stockDao;

    @Override
    public PageBeanVo<Stock> findList(PageBeanVo page) {
        PageBeanVo<Stock> rtPage = new PageBeanVo<>();

        List<Stock> stocks = stockDao.findList(page);
        Integer total = stockDao.findTotal();

        rtPage.setData(stocks);
        rtPage.setTotal(total);
        rtPage.setMaxPageNumber(total / page.getPageSize());
        return rtPage;
    }
}