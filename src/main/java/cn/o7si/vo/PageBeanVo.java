package cn.o7si.vo;

import java.util.List;

public class PageBeanVo<T> {

    // 最小页码
    private Integer minPageNumber;
    // 当前页码
    private Integer curPageNumber;
    // 最大页码
    private Integer maxPageNumber;
    // 单页默认记录条数
    private Integer pageSize;
    // 偏移
    private Integer offset;
    // 总量
    private Integer total;
    // 数据
    private List<T> data;

    {
        minPageNumber = 1;
        curPageNumber = 1;
        maxPageNumber = null;
        pageSize = 5;
        offset = null;
        total = null;
        data = null;
    }

    public Integer getMinPageNumber() {
        return minPageNumber;
    }

    public void setMinPageNumber(Integer minPageNumber) {
        this.minPageNumber = minPageNumber;
    }

    public Integer getCurPageNumber() {
        return curPageNumber;
    }

    public void setCurPageNumber(Integer curPageNumber) {
        this.curPageNumber = curPageNumber;
    }

    public Integer getMaxPageNumber() {
        return maxPageNumber;
    }

    public void setMaxPageNumber(Integer maxPageNumber) {
        this.maxPageNumber = maxPageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (curPageNumber - 1) * pageSize;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageBeanVo{" +
                "minPageNumber=" + minPageNumber +
                ", curPageNumber=" + curPageNumber +
                ", maxPageNumber=" + maxPageNumber +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
