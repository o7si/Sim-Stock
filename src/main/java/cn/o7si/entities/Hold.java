package cn.o7si.entities;

/**
 * 股票持有情况的实体类
 */
public class Hold {

    // 编号
    private Integer id;
    // 账户编号
    private Integer uaid;
    // 钱包编号
    private Integer wid;
    // 股票编号
    private Integer sid;
    // 持有数量
    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Hold{" +
                "id=" + id +
                ", uaid=" + uaid +
                ", wid=" + wid +
                ", sid=" + sid +
                ", number=" + number +
                '}';
    }
}
