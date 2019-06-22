package cn.o7si.dao.impl;

import cn.o7si.dao.IStockDao;
import cn.o7si.entities.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stockDaoImpl")
public class StockDaoImpl implements IStockDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Stock> findAll() {
        // 查询所有股票时使用的SQL语句
        String sql = "select * from stock";

        List<Stock> stocks = null;
        try {
            // 执行SQL语句
            stocks = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Stock.class));
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回查询数据
        return stocks;
    }
}
