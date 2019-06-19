package cn.o7si.controller;

import cn.o7si.entities.Information;
import cn.o7si.service.IUserInformationService;
import cn.o7si.utils.JwtUtils;
import cn.o7si.utils.StatusCodeUtils;
import cn.o7si.utils.TextUtils;
import cn.o7si.vo.ResponseData;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

// 用户信息控制层
@RequestMapping("/user/information")
@Controller("userInformationController")
public class UserInformationController {

    @Autowired
    private IUserInformationService userInformationService;

    // 功能：查询个人信息
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData find(@RequestBody Map<String, Object> data) {
        System.out.println((String) data.get("token"));
        // 获取登录状态
        Map<String, Claim> claim = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claim != null ? claim.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN);

        // 查询当前账户的详细信息
        Information rtInfo = userInformationService.findInformation(accountId);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (rtInfo != null) {
            // 查找到相关数据
            rtData.setAction(null);
            rtInfo.erase();
            rtData.put("information", rtInfo);
            rtData.setStatusCode(StatusCodeUtils.INFORMATIONFINDSUCCESS);
            rtData.setDesc("用户信息查询成功");
        } else {
            // 未查找到相关数据
            rtData.setAction(null);
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.INFORMATIONFINDFAILURE);
            rtData.setDesc("用户信息查询失败");
        }

        return rtData;
    }

    // 功能：修改个人信息
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData modify(@RequestBody Map<String, Object> data) {
        // 获取登录状态
        Map<String, Claim> claim = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claim != null ? claim.get("accountId").asInt() : null;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN);

        // 获取进行修改的相关数据
        String field = (String) data.get("field");
        Object value = data.get("value");

        // 提供参数不足
        if (TextUtils.isEmpty(field))
            return new ResponseData(StatusCodeUtils.MISSPARAM);

        // 修改字段不满足要求
        if (!Information.isLegalField(field))
            return new ResponseData(StatusCodeUtils.BADINFORMATIONFIELD);

        // 调用业务层修改个人信息
        boolean rtValue = userInformationService.modifyOrdinaryInformation(field, value, accountId);

        // 响应给客户端的数据
        ResponseData rtData = new ResponseData();

        // 设置返回值
        if (rtValue) {
            // 修改成功
            rtData.setAction(null);
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.INFORMATIONMODIFYSUCCESS);
            rtData.setDesc("用户信息修改成功");
        } else {
            // 修改失败
            rtData.setAction(null);
            rtData.setData(null);
            rtData.setStatusCode(StatusCodeUtils.INFORMATIONMODIFYFAILURE);
            rtData.setDesc("用户信息修改失败");
        }

        return rtData;
    }

    // 功能：上传头像（暂不可用）
    @RequestMapping(value = "/upload/avatar", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData uploadAvatar(Map<String, Object> data, HttpSession session, MultipartFile upload) {
        // 获取登录状态
        Map<String, Claim> claim = JwtUtils.verifyToken((String) data.get("token"));
        Integer accountId = claim != null ? claim.get("accountId").asInt() : null;
        System.out.println(accountId);
//        Integer accountId;
        accountId = 1;

        // 如果用户未登录
        if (accountId == null)
            return new ResponseData(StatusCodeUtils.NOTLOGGEDIN);

        // 头像上传路径
        String path = session.getServletContext().getRealPath("uploads/avatar/");

        // 如果路径未被创建，则创建该路径
        File tmpFile = new File(path);
        if (!tmpFile.exists())
            tmpFile.mkdirs();

        // 获取文件名
        String fileName = upload.getOriginalFilename();
        // 随机值
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 拼接文件名
        String finalName = uuid + "_" + fileName;

        // 获取文件扩展名
        String suffix = finalName.substring(finalName.lastIndexOf(".") + 1);

        System.out.println(suffix);

        // 非图像文件
        if (!suffix.equals("jpg") && !suffix.equals("png") && !suffix.equals("gif"))
            return new ResponseData(StatusCodeUtils.FILENOTIMAGE);

        try {
            // 上传文件
            upload.transferTo(new File(path, finalName));

            // 修改数据表中头像路径
            boolean rtValue = userInformationService.modifyAvatarInformation(path + finalName, accountId);

            // 响应给客户端的数据
            ResponseData rtData = new ResponseData();

            // 设置返回值
            if (rtValue) {
                // 修改成功
                rtData.setAction(null);
                rtData.setData(null);
                rtData.setStatusCode(StatusCodeUtils.AVATARMODIFYSUCCESS);
                rtData.setDesc("头像修改成功");
            } else {
                // 修改失败
                rtData.setAction(null);
                rtData.setData(null);
                rtData.setStatusCode(StatusCodeUtils.AVATARMODIFYFAILURE);
                rtData.setDesc("头像修改失败");
            }

            return rtData;
        } catch (IOException ignored) {
            // 忽略异常
        }
        // 文件上传失败
        return new ResponseData(StatusCodeUtils.UNKNOWERROR);
    }
}
