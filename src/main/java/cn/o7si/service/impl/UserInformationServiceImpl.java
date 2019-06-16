package cn.o7si.service.impl;

import cn.o7si.dao.IUserInformationDao;
import cn.o7si.entities.Information;
import cn.o7si.service.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInformationService")
public class UserInformationServiceImpl implements IUserInformationService {

    @Autowired
    private IUserInformationDao userInformationDao;

    @Override
    public Information findInformation(Integer accountId) {
        Information rtInformation = userInformationDao.findInformationByAccountId(accountId);
        return rtInformation;
    }
}
