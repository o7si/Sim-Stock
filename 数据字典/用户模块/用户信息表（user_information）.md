**字段说明**

| 字段 | 类型 | 主键 | 外键 | 唯一 | 自增 | 非空 | 默认值 | 备注 |
| --- | --- | :-: | :-: | :-: | :-: | :-: | :-: |
| id | int | Y |   |   | Y | Y |  | 个人信息ID |
| avatar | varchar(50) |   |   |   |   |   |   | 头像路径 |
| nickname | varchar(20) |   |   |   |   |   |   | 昵称 |
| gender | int |   |   |   |   |   | 0 | 性别 |
| birthday | date |   |   |   |   |   |   | 生日 |
| place | varchar(30) |   |   |   |   |   |   | 所在地 |
| industry | int |   |   |   |   |   |   | 兴趣爱好 |
| phone | varchar(15) |   |   |   |   |   |   | 电话号码 |
| email | varchar(25) |   |   |   |   |   |   | 电子邮件 |
| profile | varchar(300) |   |   |   |   |   |   | 个人简介 |
| uaid | int |   | Y |   |   |   |   | 账户ID |

**SQL语句**
```
CREATE TABLE user_information
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    avatar varchar(50),
    nickname varchar(20),
    gender int DEFAULT 0,
    birthday date,
    place varchar(30),
    industry int,
    phone varchar(15),
    email varchar(35),
    profile varchar(300),
    uaid int,
    CONSTRAINT user_information_user_account_id_fk FOREIGN KEY (uaid) REFERENCES user_account (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```