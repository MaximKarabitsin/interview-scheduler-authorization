package com.netcracker.interviewschedulerauthorization.model;

import java.util.List;

public class JSONResponse {
    private long total;
    private List list;

    public JSONResponse() {
    }

    public JSONResponse(long total, List list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
