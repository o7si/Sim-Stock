package cn.o7si.utils;

public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction() {
        System.out.println("开启事务");
        try {
            connectionUtils.getThreadConnect().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        System.out.println("提交事务");
        try {
            connectionUtils.getThreadConnect().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        System.out.println("回滚事务");
        try {
            connectionUtils.getThreadConnect().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void release() {
        System.out.println("释放连接");
        try {
            // 还给连接池
            connectionUtils.getThreadConnect().close();
            connectionUtils.removeConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
