package cn.o7si.vo;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {

    // 数据
    private Map<String, Object> data;
    // 状态码
    private Integer statusCode;
    // 描述
    private String desc;

    public ResponseData() {
        data = null;
        statusCode = null;
        desc = null;
    }

    public ResponseData(Integer statusCode) {
        data = null;
        this.statusCode = statusCode;
        desc = null;
    }

    public ResponseData(Integer statusCode, String desc) {
        data = null;
        this.statusCode = statusCode;
        this.desc = desc;
    }

    public ResponseData(Map<String, Object> data, Integer statusCode, String desc) {
        this.data = data;
        this.statusCode = statusCode;
        this.desc = desc;
    }

    public void put(String key, Object value) {
        if (data == null)
            data = new HashMap<>();
        data.put(key, value);
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
                ", data=" + data +
                ", statusCode=" + statusCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
