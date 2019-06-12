package cn.o7si.entities;

import java.util.Date;

public class Account {

    private static final Integer DEFAULTAUTHORITY = 0;

    private Integer id;
    private String username;
    private String password;
    private Integer authority;
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
