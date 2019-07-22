**字段说明**

| 字段 | 类型 | 主键 | 唯一 | 自增 | 非空 | 默认值 | 备注 |
| --- | --- | :-: | :-: | :-: | :-: | :-: | --- |
| id | int | Y |   | Y | Y |   | 账号标识 |
| username | varchar(20) |   | Y |   | Y |   | 账号名称 |
| password | varchar(20) |   |   |   | Y |   | 账号密码 |
| authority | int |   |   |   | Y | 0 | 账号权限 |
| create_time | timestamp |   |   |   | Y | current_timestamp | 创建日期 |

**SQL语句**
```
CREATE TABLE user_account
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL,
    password varchar(20) NOT NULL,
    authority int DEFAULT 0 NOT NULL,
    create_time timestamp DEFAULT current_timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX user_account_username_uindex ON user_account (username);
```