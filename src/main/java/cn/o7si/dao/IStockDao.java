package cn.o7si.dao;

import cn.o7si.entities.Stock;
import cn.o7si.vo.PageBeanVo;

import java.util.List;

/**
 * 股票相关操作的持久层接口
 */
public interface IStockDao {

    /**
     * 查询所有股票信息
     * @return      查询到的股票信息
     */
    List<Stock> findAll();

    /**
     * 查询股票信息（分页）
     * @param page      分页信息
     * @return          查询到的部分股票信息
     */
    List<Stock> findList(PageBeanVo page);

    /**
     * 查询股票数量
     * @return          查询到的股票数量
     */
    Integer findTotal();

    /**
     * 批量修改股票信息
     * @param stocks    将要修改的股票信息
     * @return          如果修改成功则返回true，否则返回false
     */
    boolean batchUpdate(List<Stock> stocks);

    /**
     * 根据ID获取股票信息
     * @param stockId   股票ID
     * @return          如果查询成功则返回股票信息，否则返回null
     */
    Stock findStockById(Integer stockId);
}
