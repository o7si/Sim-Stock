package cn.o7si.vo;

public class ResponseData<T> {

    // 操作
    private String operate;
    // 响应数据
    private T data;
    // 状态码
    private Integer statusCode;
    // 描述
    private String desc;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
                "operate='" + operate + '\'' +
                ", data=" + data +
                ", statusCode=" + statusCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
