package cn.o7si.service;

import cn.o7si.entities.Market;

import java.util.List;

/**
 * 市场变化相关操作的业务层接口
 */
public interface IMarketService {

    /**
     * 获取最近一段时间某支股票的变化情况
     * @param recordNumber      记录条数
     * @param stockId           股票编号
     * @return                  返回查询到的变化信息
     */
    List<Market> getLateList(Integer recordNumber, Integer stockId);
}
