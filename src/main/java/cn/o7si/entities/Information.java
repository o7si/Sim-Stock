package cn.o7si.entities;

import java.io.Serializable;
import java.util.Date;

public class Information implements Serializable {

    // 默认性别
    private static final Integer DEFAULTGENDER = 0;

    // 个人信息ID
    private Integer id;
    // 头像
    private String avatar;
    // 昵称
    private String nickname;
    // 性别
    // 0 -> 保密
    // 1 -> 男
    // 2 -> 女
    // 3 -> 其他
    private Integer gender;
    // 生日
    private Date birthday;
    // 地区
    private String place;
    // 行业
    private Integer industry;
    // 电话号码
    private String phone;
    // 电子邮件
    private String email;
    // 个人简介
    private String profile;
    // 账户ID
    private Integer uaid;

    public static boolean isLegalField(String field) {
        return  field.equals("avatar") ||
                field.equals("nickname") ||
                field.equals("gender") ||
                field.equals("birthday") ||
                field.equals("place") ||
                field.equals("industry") ||
                field.equals("profile");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getUaid() {
        return uaid;
    }

    public void setUaid(Integer uaid) {
        this.uaid = uaid;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", place='" + place + '\'' +
                ", industry=" + industry +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", profile='" + profile + '\'' +
                ", uaid=" + uaid +
                '}';
    }
}
