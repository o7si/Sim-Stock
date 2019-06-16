package cn.o7si.dao;

import cn.o7si.entities.Account;
import cn.o7si.entities.Information;

public interface IUserInformationDao {

    Information findInformationByAccountId(Integer accountId);

    boolean updateInformation(Information info);
}
