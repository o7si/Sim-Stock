package cn.o7si.service;

import cn.o7si.entities.Information;

public interface IUserInformationService {

    Information findInformation(Integer accountId);

    boolean modifyOrdinaryInformation(String field, Object value, Integer accountId);

    boolean modifyAvatarInformation(String finalName, Integer accountId);
}
