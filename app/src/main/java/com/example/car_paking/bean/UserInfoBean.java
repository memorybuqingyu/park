package com.example.car_paking.bean;

public class UserInfoBean {

    /**
     * code : 200
     * message : 查询成功
     * success : false
     * data : {"id":"235bafd68296404fbc775178a2fb0321","userName":"test01","password":"$2a$10$/SbM8OmIekwhV67auKvFcecnM5XBGLFetsjOgMid7z6k6FXY2wjZe","newPas":null,"phone":"1547846846","roles":"普通用户","avatar":"https://cdn.sunofbeaches.com/images/default_avatar.png","email":"memorybuqingyu@163.com","sign":"我的目标是星辰大海!","state":"1","regIp":"127.0.0.1","loginIp":"127.0.0.1","createTime":"2021-04-26T10:53:56.000+00:00","updateTime":"2021-04-26T10:53:56.000+00:00","emailCode":null}
     */

    private int code;
    private String message;
    private boolean success;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 235bafd68296404fbc775178a2fb0321
         * userName : test01
         * password : $2a$10$/SbM8OmIekwhV67auKvFcecnM5XBGLFetsjOgMid7z6k6FXY2wjZe
         * newPas : null
         * phone : 1547846846
         * roles : 普通用户
         * avatar : https://cdn.sunofbeaches.com/images/default_avatar.png
         * email : memorybuqingyu@163.com
         * sign : 我的目标是星辰大海!
         * state : 1
         * regIp : 127.0.0.1
         * loginIp : 127.0.0.1
         * createTime : 2021-04-26T10:53:56.000+00:00
         * updateTime : 2021-04-26T10:53:56.000+00:00
         * emailCode : null
         */

        private String id;
        private String userName;
        private String password;
        private Object newPas;
        private String phone;
        private String roles;
        private String avatar;
        private String email;
        private String sign;
        private String state;
        private String regIp;
        private String loginIp;
        private String createTime;
        private String updateTime;
        private Object emailCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getNewPas() {
            return newPas;
        }

        public void setNewPas(Object newPas) {
            this.newPas = newPas;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getRegIp() {
            return regIp;
        }

        public void setRegIp(String regIp) {
            this.regIp = regIp;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getEmailCode() {
            return emailCode;
        }

        public void setEmailCode(Object emailCode) {
            this.emailCode = emailCode;
        }
    }
}
