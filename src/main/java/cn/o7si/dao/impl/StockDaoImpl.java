package cn.o7si.dao.impl;

import cn.o7si.dao.IStockDao;
import cn.o7si.entities.Stock;
import cn.o7si.vo.PageBeanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stockDaoImpl")
public class StockDaoImpl implements IStockDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Stock> findList(PageBeanVo page) {
        // 查询股票时使用的SQL语句
        String sql = "select * from stock limit ?, ?";

        List<Stock> stocks = null;
        try {
            // 执行SQL语句
            stocks = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Stock.class), page.getOffset(), page.getPageSize());
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回查询数据
        return stocks;
    }

    @Override
    public Integer findTotal() {
        // 查询股票数量时使用的SQL语句
        String sql = "select count(*) from stock";

        Integer total = 0;
        try {
            // 执行SQL语句
            total = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回查询结果
        return total;
    }
}
