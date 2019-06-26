package cn.o7si.entities;

/**
 * 交易信息实体类
 */
public class Transaction {

    // 交易编号
    private Integer id;
    // 交易数量
    private Integer number;
    // 单股售价
    private Double single;
    // 交易额
    private Double turnover;
    // 交易类型
    private Integer type;
    // 账户编号
    private Integer uaid;
    // 钱包编号
    private Integer wid;
    // 股票编号
    private Integer sid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getSingle() {
        return single;
    }

    public void setSingle(Double single) {
        this.single = single;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUaid() {
        return uaid;
    }

    public void setUaid(Integer uaid) {
        this.uaid = uaid;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", number=" + number +
                ", single=" + single +
                ", turnover=" + turnover +
                ", type=" + type +
                ", uaid=" + uaid +
                ", wid=" + wid +
                ", sid=" + sid +
                '}';
    }
}
