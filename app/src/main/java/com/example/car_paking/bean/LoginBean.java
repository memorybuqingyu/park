package com.example.car_paking.bean;

public class LoginBean {
    /**
     * code : 200
     * message : 登录成功!
     * success : false
     * data : b8d50c1041edd8a3307eb44447a39ee4
     */

    private int code;
    private String message;
    private boolean success;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
