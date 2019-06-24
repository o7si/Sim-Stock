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

        List<Stock> stocks = stockDao.findList(page);
        Integer total = stockDao.findTotal();

        PageBeanVo rtPage = new PageBeanVo();
        rtPage.setCurPageNumber(page.getCurPageNumber());
        rtPage.setMaxPageNumber((int) Math.ceil(total * 1.0 / page.getPageSize()));
        rtPage.setPageSize(page.getPageSize());
        rtPage.setOffset(rtPage.getOffset());
        rtPage.setTotal(total);
        rtPage.setData(stocks);

        return rtPage;
    }
}