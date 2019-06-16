package cn.o7si.controller;

import cn.o7si.dao.IUserInformationDao;
import cn.o7si.entities.Information;
import cn.o7si.service.IUserInformationService;
import cn.o7si.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("userInformationController")
@RequestMapping("/user/information")
public class UserInformationController {

    @Autowired
    private IUserInformationService userInformationService;

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData find(HttpServletRequest request) {
        // 获取Session中保存的账户ID
        Integer accountId = (Integer) request.getSession().getAttribute("currentAccountId");

        // 查询当前账户的详细信息
        Information rtInfo = userInformationService.findInformation(1);
        System.out.println(rtInfo);
        return null;
    }
}
