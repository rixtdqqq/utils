package com.zhuyx.mytraining.entity;

/**
 * 服务器接口返回结果相同，用泛型来代替继承
 * Created by zhuyingxin on 2016/6/7.
 * email : rixtdqqq_2015@163.com
 */
public class ResponseEntity<T> {
    private int status;
    private String msg;
    /**
     * 如果返回的结构中不是data,需要使用 FieldNamingStrategy来转换
     */
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
