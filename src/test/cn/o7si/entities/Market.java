package cn.o7si.entities;

import java.util.Date;

public class Market {

    // 市场编号
    private Integer id;
    // 变动率
    private Double percent;
    // 变动时间
    private Date time;
    // 股票编号
    private Integer sid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id=" + id +
                ", percent=" + percent +
                ", time=" + time +
                ", sid=" + sid +
                '}';
    }
}
