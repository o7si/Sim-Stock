package cn.o7si.dao;

import cn.o7si.entities.Information;

public interface IUserInformationDao {

    /**
     * 为账户创建个人信息记录
     * @param accountId     账户ID
     * @return              如果创建成功则返回true，否则返回false
     */
    boolean createInformation(Integer accountId);

    /**
     * 根据账户ID查找个人信息
     * @param accountId     用作查找的账户ID
     * @return              如果查找成功则返回个人信息，否则返回null
     */
    Information findInformationByAccountId(Integer accountId);

    /**
     * 修改个人信息
     * @param field         将要修改的字段
     * @param value         要修改成为的值
     * @param accountId     账户ID
     * @return              如果修改成功则返回true，否则返回false
     */
    boolean updateOrdinaryInformation(String field, Object value, Integer accountId);
}
