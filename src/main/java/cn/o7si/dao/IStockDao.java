package cn.o7si.dao;

import cn.o7si.entities.Stock;

import java.util.List;

public interface IStockDao {

    List<Stock> findAll();
}
