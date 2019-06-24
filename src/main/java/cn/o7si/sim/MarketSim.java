package cn.o7si.sim;

import cn.o7si.dao.IMarketDao;
import cn.o7si.dao.IStockDao;
import cn.o7si.entities.Market;
import cn.o7si.entities.Stock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("marketSim")
public class MarketSim {

    private static IMarketDao marketDao;

    @Autowired
    public void setMarketDao(IMarketDao marketDao) {
        MarketSim.marketDao = marketDao;
    }

    private static IStockDao stockDao;

    @Autowired
    public void setStockDao(IStockDao stockDao) {
        MarketSim.stockDao = stockDao;
    }

    public static void simulate() {

        // 获取所有股票
        List<Stock> stocks = stockDao.findAll();
        // 如果未查询到股票信息
        if (stocks == null || stocks.size() == 0)
            return;

        List<Market> markets = new ArrayList<>();

        for (Stock stock : stocks) {

            Market market = new Market();
            market.setPrePrice(stock.getPrice());

            // 股票变化率
            Double percent = Math.random() * 0.2 - 0.1;
            // 调整相关属性
            stock.setMarketValue(stock.getMarketValue() * (1 + percent));
            stock.setPrice(stock.getMarketValue() / stock.getTotal());

            market.setPercent(percent);
            market.setPostPrice(stock.getPrice());
            market.setChangePrice(market.getPostPrice() - market.getPrePrice());

            markets.add(market);
        }

        // 插入记录
        marketDao.batchInsert(markets);
        // 修改记录
        stockDao.batchUpdate(stocks);
    }
}
