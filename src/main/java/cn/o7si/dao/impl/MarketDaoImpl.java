package cn.o7si.dao.impl;

import cn.o7si.dao.IMarketDao;
import cn.o7si.entities.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository("marketDaoImpl")
public class MarketDaoImpl implements IMarketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean batchInsert(List<Market> markets) {
        // 批量插入时使用的SQL语句
        String sql = "insert into market(percent, sid) values(?, ?)";

        try {
            // 执行SQL语句
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
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
            return true;
        } catch (Exception ignored) {
            // 忽略异常
            return false;
        }
    }
}
