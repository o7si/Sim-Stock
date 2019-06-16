package cn.o7si.controller;

import cn.o7si.dao.IUserInformationDao;
import cn.o7si.entities.Information;
import cn.o7si.service.IUserInformationService;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

// 用户信息控制层
@RequestMapping("/user/information")
@Controller("userInformationController")
public class UserInformationController {

    @Autowired
    private IUserInformationService userInformationService;

    // 功能：查询个人信息
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData find(HttpServletRequest request) {
        // 获取相关数据
        Integer accountId = (Integer) request.getSession().getAttribute("currentAccountId");

        // 查询当前账户的详细信息
        Information rtInfo = userInformationService.findInformation(accountId);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (rtInfo != null) {
            // 查找到相关数据
            rtData.setAction("");
            rtData.put("information", rtInfo);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTEXIST);
            rtData.setDesc("用户信息查询成功");
        } else {
            // 未查找到相关数据
            rtData.setAction("");
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.ACCOUNTNOTEXIST);
            rtData.setDesc("用户信息查询失败");
        }

        return rtData;
    }

    // 功能：修改个人信息
    @RequestMapping("/modify")
    public void tmp() {

    }
}
