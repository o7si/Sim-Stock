package cn.o7si.service.impl;

import cn.o7si.dao.IMarketDao;
import cn.o7si.entities.Market;
import cn.o7si.service.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("marketServiceImpl")
public class MarketServiceImpl implements IMarketService {

    @Autowired
    private IMarketDao marketDao;

    @Override
    public List<Market> getLateList(Integer recordNumber, Integer stockId) {
        List<Market> rtMarket = marketDao.getLateList(recordNumber, stockId);
        return rtMarket;
    }
}
