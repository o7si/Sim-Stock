package cn.o7si.dao;

import cn.o7si.entities.Market;

import java.util.List;

/**
 * 市场相关操作的持久层接口
 */
public interface IMarketDao {

    /**
     * 批量插入市场变化信息
     * @param markets   将要插入的市场变化信息
     * @return          如果插入成功则返回true，否则返回false
     */
    boolean batchInsert(List<Market> markets);
}
