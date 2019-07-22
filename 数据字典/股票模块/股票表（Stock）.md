**字段说明**

| 字段 | 类型 | 主键 | 外键 | 唯一 | 自增 | 非空 | 默认值 | 备注 |
| --- | --- | :-: | :-: | :-: | :-: | :-: | :-: |
| id | int | Y |   |   | Y | Y |  | 股票ID |
| name | varchar(32) |   |   | Y |   | Y |   | 股票名称 |
| market_value | double |   |   |   |   | Y | 0 | 股票市值 |
| price | double |   |   |   |   | Y | 0 | 单股售价 |
| total | int |   |   |   |   | Y | 0 | 总股数 |
| sold | int |   |   |   |   | Y | 0 | 售出股数 |
| hold | int |   |   |   |   | Y | 0 | 持有股数 |
| appear | timestamp |   |   |   |   | Y | current_timestamp | 上市时间 |

**SQL语句**

```
CREATE TABLE stock
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(32) NOT NULL,
    market_value double DEFAULT 0 NOT NULL,
    price double DEFAULT 0 NOT NULL,
    total int DEFAULT 0 NOT NULL,
    sold int DEFAULT 0 NOT NULL,
    hold int DEFAULT 0 NOT NULL,
    appear timestamp DEFAULT current_timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX stock_name_uindex ON stock (name);
```

**初始数据**

```
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('AAAA', 10000000, 20, 500000, 0, 500000, '2019-06-01 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('BBBB', 2000000, 5, 400000, 0, 400000, '2019-06-02 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('CCCC', 7000000, 7, 1000000, 0, 1000000, '2019-06-03 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('DDDD', 20000000, 10, 2000000, 0, 2000000, '2019-06-04 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('EEEE', 30000000, 6, 5000000, 0, 5000000, '2019-06-05 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('FFFF', 4000000, 4, 1000000, 0, 1000000, '2019-06-06 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('GGGG', 3000000, 3, 1000000, 0, 1000000, '2019-06-07 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('HHHH', 200000000, 40, 5000000, 0, 5000000, '2019-06-08 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('IIII', 1000000, 2, 500000, 0, 500000, '2019-06-09 00:00:00');
INSERT INTO `sim_stock`.`stock` (`name`, `market_value`, `price`, `total`, `sold`, `hold`, `appear`) VALUES ('JJJJ', 8000000, 8, 1000000, 0, 1000000, '2019-06-10 00:00:00');
```