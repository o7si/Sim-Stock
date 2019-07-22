**字段说明**

| 字段 | 类型 | 主键 | 外键 | 唯一 | 自增 | 非空 | 默认值 | 备注 |
| --- | --- | :-: | :-: | :-: | :-: | :-: | :-: |
| id | int | Y |   |   | Y | Y |  | 交易编号 |
| number | int |   |   |   |   | Y | 0 | 交易数量 |
| single | double |   |   |   |   | Y | 0 | 单股价格 |
| turnover | double |   |   |   |   | Y | 0 | 交易额 |
| type | int |   |   |   |   | Y |   | 交易类型（0 -> 购入，1 -> 售出） |
| time | timestamp |   |   |   |   | Y | current_timestamp | 交易时间 |
| uaid | int |   | Y |   |   | Y |   | 账户编号 |
| wid | int |   | Y |   |   | Y |   | 钱包编号 |
| sid | int |   | Y |   |   | Y |   | 股票编号 |

**SQL语句**
```
CREATE TABLE transaction
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    number int DEFAULT 0 NOT NULL,
    single double DEFAULT 0 NOT NULL,
    turnover double DEFAULT 0 NOT NULL,
    type int NOT NULL,
    time timestamp DEFAULT current_timestamp NOT NULL,
    uaid int NOT NULL,
    wid int NOT NULL,
    sid int NOT NULL,
    CONSTRAINT transaction_user_account_id_fk FOREIGN KEY (uaid) REFERENCES user_account (id),
    CONSTRAINT transaction_wallet_id_fk FOREIGN KEY (wid) REFERENCES wallet (id),
    CONSTRAINT transaction_stock_id_fk FOREIGN KEY (sid) REFERENCES stock (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```