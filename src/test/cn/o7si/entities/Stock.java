package cn.o7si.entities;

import java.util.Date;

public class Stock {

    // 股票编号
    private Integer id;
    // 股票名称
    private String name;
    // 股票市值
    private Double marketValue;
    // 单股价格
    private Double price;
    // 总股数
    private Integer total;
    // 售出股数
    private Integer sold;
    // 持有股数
    private Integer hold;
    // 上市时间
    private Date appear;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getHold() {
        return hold;
    }

    public void setHold(Integer hold) {
        this.hold = hold;
    }

    public Date getAppear() {
        return appear;
    }

    public void setAppear(Date appear) {
        this.appear = appear;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marketValue=" + marketValue +
                ", total=" + total +
                ", sold=" + sold +
                ", hold=" + hold +
                ", appear=" + appear +
                '}';
    }
}
