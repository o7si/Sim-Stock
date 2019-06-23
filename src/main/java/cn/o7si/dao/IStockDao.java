package cn.o7si.dao;

import cn.o7si.entities.Stock;
import cn.o7si.vo.PageBeanVo;

import java.util.List;

public interface IStockDao {

    List<Stock> findList(PageBeanVo page);

    Integer findTotal();
}
