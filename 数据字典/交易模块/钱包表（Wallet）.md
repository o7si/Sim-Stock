**字段说明**

| 字段 | 类型 | 主键 | 外键 | 唯一 | 自增 | 非空 | 默认值 | 备注 |
| --- | --- | :-: | :-: | :-: | :-: | :-: | :-: |
| id | int | Y |   |   | Y | Y |  | 钱包编号 |
| balance | double |   |   |   |   | Y | 10000 | 余额 |
| time | timestamp |   |   |   |   | Y | current_timestamp | 开户时间 |
| password | int(6) |   |   |   |   | Y | 123456 | 交易密码 |
| uaid | int |   | Y |   |   | Y |   | 账户编号 |

**SQL语句**
```
CREATE TABLE wallet
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    balance double DEFAULT 10000 NOT NULL,
    time timestamp DEFAULT current_timestamp NOT NULL,
    password int(6) DEFAULT 123456 NOT NULL,
    uaid int NOT NULL,
    CONSTRAINT wallet_user_account_id_fk FOREIGN KEY (uaid) REFERENCES user_account (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=100000;
```