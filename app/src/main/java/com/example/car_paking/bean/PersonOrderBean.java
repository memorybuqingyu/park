package com.example.car_paking.bean;

import java.util.List;

public class PersonOrderBean {

    /**
     * code : 200
     * message : 请求成功
     * success : false
     * data : [{"id":"e4b78d35454e4e608e4d940f65646951","userId":"830e69034e9f4ac5afbaaa2c17bfd09e","parkingSpaceId":"2c3a8666b30d4208910cfa37ebd8ab1c","plateNum":"川A0CBBB","endTime":"2021-05-03T14:52:31.000+08:00","type":1,"createTime":"2021-05-03T14:42:31.000+08:00","updateTime":"2021-05-03T14:42:31.000+08:00"}]
     */

    private int code;
    private String message;
    private boolean success;
    /**
     * id : e4b78d35454e4e608e4d940f65646951
     * userId : 830e69034e9f4ac5afbaaa2c17bfd09e
     * parkingSpaceId : 2c3a8666b30d4208910cfa37ebd8ab1c
     * plateNum : 川A0CBBB
     * endTime : 2021-05-03T14:52:31.000+08:00
     * type : 1
     * createTime : 2021-05-03T14:42:31.000+08:00
     * updateTime : 2021-05-03T14:42:31.000+08:00
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
        private String endTime;
        private int type;
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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
