package cn.o7si.entities;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

    // 默认权限
    private static final Integer DEFAULTAUTHORITY = 0;

    // 账户ID
    private Integer id;
    // 账户账号
    private String username;
    // 账户密码
    private String password;
    // 账户权限
    private Integer authority;
    // 账户创建日期
    private Date createTime;

    public Account() {

    }

    public Account(String username, String password) {
        this(username, password, DEFAULTAUTHORITY);
    }

    public Account(String username, String password, Integer authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public void erase() {
        password = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority=" + authority +
                ", createTime=" + createTime +
                '}';
    }
}
