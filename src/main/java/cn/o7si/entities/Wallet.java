package cn.o7si.entities;

import java.util.Date;

/**
 * 钱包的实体类
 */
public class Wallet {

    // 钱包ID
    private Integer id;
    // 钱包余额
    private Double balance;
    // 开户时间
    private Date time;
    // 交易密码
    private Integer password;
    // 账户ID
    private Integer uaid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getUaid() {
        return uaid;
    }

    public void setUaid(Integer uaid) {
        this.uaid = uaid;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", balance=" + balance +
                ", time=" + time +
                ", password=" + password +
                ", uaid=" + uaid +
                '}';
    }
}
