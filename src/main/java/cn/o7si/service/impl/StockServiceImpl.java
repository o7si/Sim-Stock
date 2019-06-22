package cn.o7si.service.impl;

import cn.o7si.dao.IStockDao;
import cn.o7si.entities.Stock;
import cn.o7si.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stockServiceImpl")
public class StockServiceImpl implements IStockService {

    @Autowired
    private IStockDao stockDao;

    @Override
    public List<Stock> findAll() {
        List<Stock> stocks = stockDao.findAll();
        return stocks;
    }
}