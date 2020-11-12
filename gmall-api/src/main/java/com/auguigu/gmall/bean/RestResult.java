package com.auguigu.gmall.bean;


import java.util.ArrayList;

public class RestResult {
    private String status;
    private String info;
    private Object data = new ArrayList();
    private int length;

    public RestResult() {
        this.setData(new ArrayList());
        this.setLength(0);
        this.setStatus(RestResultStatus.FAIL.getCode());
        this.setInfo(RestResultStatus.FAIL.getText());
    }

    public void success(Object data, int length) {
        this.setData(data);
        this.setLength(length);
        this.setStatus(RestResultStatus.SUCCESS.getCode());
        this.setInfo(RestResultStatus.SUCCESS.getText());
    }

    public void success(Object data) {
        this.setData(data);
        this.setLength(0);
        this.setStatus(RestResultStatus.SUCCESS.getCode());
        this.setInfo(RestResultStatus.SUCCESS.getText());
    }


    public void fail(String info) {
        this.setData(new ArrayList());
        this.setLength(0);
        this.setStatus(RestResultStatus.FAIL.getCode());
        this.setInfo(info);
    }

    public void fail(RestResultStatus restResultStatus) {
        this.setData(new ArrayList());
        this.setLength(0);
        this.setStatus(restResultStatus.getCode());
        this.setInfo(restResultStatus.getText());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
