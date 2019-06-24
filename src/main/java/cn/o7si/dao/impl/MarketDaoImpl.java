package cn.o7si.dao.impl;

import cn.o7si.dao.IMarketDao;
import cn.o7si.entities.Market;
import cn.o7si.entities.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("marketDaoImpl")
public class MarketDaoImpl implements IMarketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean batchInsert(List<Market> markets) {
        // 批量插入时使用的SQL语句
        String sql = "insert into market(pre_price, post_price, change_price, percent, sid) values(?, ?, ?, ?, ?)";

        try {
            // 执行SQL语句
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setDouble(1, markets.get(i).getPrePrice());
                    preparedStatement.setDouble(2, markets.get(i).getPostPrice());
                    preparedStatement.setDouble(3, markets.get(i).getChangePrice());
                    preparedStatement.setDouble(4, markets.get(i).getPercent());
                    preparedStatement.setInt(5, markets.get(i).getSid());
                }

                @Override
                public int getBatchSize() {
                    return markets.size();
                }
            });
            return true;
        } catch (Exception ignored) {
            // 忽略异常
            return false;
        }
    }

    @Override
    public List<Market> getLateList(Integer recordNumber, Integer stockId) {
        // 查询最近一段时间某支股票的变化情况时使用的SQL语句
        String sql = "select * from market where sid=? order by time desc limit ?";

        List<Market> markets = null;
        try {
            // 执行SQL语句
            markets = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Market.class), stockId, recordNumber);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回查询数据
        return markets;
    }
}
