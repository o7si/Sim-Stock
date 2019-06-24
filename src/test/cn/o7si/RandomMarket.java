package cn.o7si;

import cn.o7si.dao.IMarketDao;
import cn.o7si.dao.impl.MarketDaoImpl;
import cn.o7si.entities.Market;
import cn.o7si.entities.Stock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RandomMarket {

    @Test
    public void test() {
        IMarketDao marketDao = new MarketDaoImpl();

        List<Market> markets = marketDao.getLateList(10, 1);

        for (Market market : markets) {
            System.out.println(market);
        }
    }

}