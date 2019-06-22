package cn.o7si;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomMarket {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sim_stock");
        dataSource.setUsername("root");
        dataSource.setPassword("jw7985217128239");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void generate() {
        List<Stock> stocks = jdbcTemplate.query("select * from stock", new BeanPropertyRowMapper<>(Stock.class));

        List<Market> markets = new ArrayList<>();
        for (Stock stock : stocks) {
            Market market = new Market();
            market.setSid(stock.getId());
            market.setPercent(Math.random() * 0.2 - 0.1);
            stock.setMarketValue(stock.getMarketValue() * (1 + market.getPercent()));
            stock.setPrice(stock.getMarketValue() / stock.getTotal());
            markets.add(market);
        }

        jdbcTemplate.batchUpdate("insert into market(percent, sid) values (?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setDouble(1, markets.get(i).getPercent());
                preparedStatement.setInt(2, markets.get(i).getSid());
            }

            @Override
            public int getBatchSize() {
                return markets.size();
            }
        });

        jdbcTemplate.batchUpdate("update stock set market_value=?, price=? where id=?", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setDouble(1, stocks.get(i).getMarketValue());
                preparedStatement.setDouble(2, stocks.get(i).getPrice());
                preparedStatement.setInt(3, stocks.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return stocks.size();
            }
        });

        System.out.println(markets);
    }

}