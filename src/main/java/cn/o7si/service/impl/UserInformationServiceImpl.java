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
        // 调用持久层根据用户账户ID查询用户信息
        Information rtInfo = userInformationDao.findInformationByAccountId(accountId);

        // 如果未查询到结果（信息表中未创建对应记录对应用户账户）
        if (rtInfo == null) {
            // 向表中添加用户信息
            boolean rtValue = userInformationDao.createInformation(accountId);

            // 如果添加失败
            if (!rtValue)
                return null;

            // 如果添加成功
            return new Information();
        }

        return rtInfo;
    }

    @Override
    public boolean modifyOrdinaryInformation(String field, Object value, Integer accountId) {
        boolean rtValue = userInformationDao.updateOrdinaryInformation(field, value, accountId);
        return rtValue;
    }
}
