package cn.o7si.service;

import cn.o7si.entities.Information;

public interface IUserInformationService {

    Information findInformation(Integer accountId);
}
