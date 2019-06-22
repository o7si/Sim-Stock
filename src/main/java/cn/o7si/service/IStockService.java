package cn.o7si.service;

import cn.o7si.entities.Stock;

import java.util.List;

public interface IStockService {

    List<Stock> findAll();
}
