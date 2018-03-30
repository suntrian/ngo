package org.ngo.basic.model;

import java.util.Collection;

public class ResultVO {

    private Integer count;
    private Object data;
    private Boolean success;
    private Integer start;
    private Integer limit;
    private Integer totalCount;
    private Boolean dir;

    public ResultVO(Object result) {
        this.data = result;
        setCount();
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setCount() {
        if (data instanceof Collection) {
            this.count = ((Collection) data).size();
        }
        else {
            this.count = 1;
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        setCount();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getDir() {
        return dir;
    }

    public void setDir(Boolean dir) {
        this.dir = dir;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
