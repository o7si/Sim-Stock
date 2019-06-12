package cn.o7si.vo;

import cn.o7si.utils.StatusCodeUtils;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {

    // 操作类型
    private String action;
    // 返回数据
    private Map<String, Object> data;
    // 状态码
    private Integer statusCode;
    // 描述
    private String desc;

    public ResponseData() {
        this.action = "";
        this.data = new HashMap<>();
        this.statusCode = StatusCodeUtils.DEFAULT;
        this.desc = "";
    }

    public void addData(String key, Object value) {
        data.put(key, value);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "action='" + action + '\'' +
                ", data=" + data +
                ", statusCode=" + statusCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
