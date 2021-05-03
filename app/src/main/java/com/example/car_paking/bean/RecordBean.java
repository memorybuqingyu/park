package com.example.car_paking.bean;

import java.util.List;

/**
 * 停车记录解析类
 */
public class RecordBean {

    /**
     * code : 200
     * message : 请求成功
     * success : false
     * data : [{"id":"733f7afc57134b00a3995bf230023539","userId":"830e69034e9f4ac5afbaaa2c17bfd09e","parkingSpaceId":"38a2db9709cd4038b56f05152c7f2e1e","plateNum":"123","money":1,"type":1,"startTime":"2021-05-03T02:22:15.000+00:00","endTime":"2021-05-03T02:22:15.000+00:00","createTime":"2021-05-03T02:22:15.000+00:00","updateTime":"2021-05-03T02:22:15.000+00:00"}]
     */

    private int code;
    private String message;
    private boolean success;
    /**
     * id : 733f7afc57134b00a3995bf230023539
     * userId : 830e69034e9f4ac5afbaaa2c17bfd09e
     * parkingSpaceId : 38a2db9709cd4038b56f05152c7f2e1e
     * plateNum : 123
     * money : 1.0
     * type : 1
     * startTime : 2021-05-03T02:22:15.000+00:00
     * endTime : 2021-05-03T02:22:15.000+00:00
     * createTime : 2021-05-03T02:22:15.000+00:00
     * updateTime : 2021-05-03T02:22:15.000+00:00
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String userId;
        private String parkingSpaceId;
        private String plateNum;
        private double money;
        private int type;
        private String startTime;
        private String endTime;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getParkingSpaceId() {
            return parkingSpaceId;
        }

        public void setParkingSpaceId(String parkingSpaceId) {
            this.parkingSpaceId = parkingSpaceId;
        }

        public String getPlateNum() {
            return plateNum;
        }

        public void setPlateNum(String plateNum) {
            this.plateNum = plateNum;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
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
    }
}
