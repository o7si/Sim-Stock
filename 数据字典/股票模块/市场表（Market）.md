**字段说明**

| 字段 | 类型 | 主键 | 外键 | 唯一 | 自增 | 非空 | 默认值 | 备注 |
| --- | --- | :-: | :-: | :-: | :-: | :-: | :-: |
| id | int | Y |   |   | Y | Y |  | 市场变化ID |
| pre_price | double |   |   |   |   | Y | 0 | 变化前估计 |
| post_price | double |   |   |   |   | Y | 0 | 变化后股价 |
| change_price | double |   |   |   |   | Y | 0 | 变化股价 |
| percent | double |   |   |   |   | Y | 0 | 变化率 |
| time | timestamp |   |   |   |   | Y | current_timestamp | 变化时间 |
| sid | int |   | Y |   |   | Y |   | 股票ID |

**SQL语句**

```
CREATE TABLE market
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    pre_price double DEFAULT 0 NOT NULL,
    post_price double DEFAULT 0 NOT NULL,
    change_price double DEFAULT 0 NOT NULL,
    percent double DEFAULT 0 NOT NULL,
    time timestamp DEFAULT current_timestamp NOT NULL,
    sid int NOT NULL,
    CONSTRAINT market_stock_id_fk FOREIGN KEY (sid) REFERENCES stock (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```