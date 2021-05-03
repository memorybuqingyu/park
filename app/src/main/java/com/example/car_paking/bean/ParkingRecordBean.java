package com.example.car_paking.bean;

/**
 * 通过停车记录中反查停车位
 */
public class ParkingRecordBean {

    /**
     * code : 200
     * message : 请求成功
     * success : false
     * data : {"id":"160baf7eadf54b05b766e01427857ff7","parkingId":"15b721f16ae14d0fa519407de1096a10","parkingSpaceName":"b05","isNull":"true","isOrder":"false","type":1,"createTime":"2021-05-01T12:12:56.000+08:00","updateTime":"2021-05-03T12:48:56.000+08:00"}
     */

    private int code;
    private String message;
    private boolean success;
    /**
     * id : 160baf7eadf54b05b766e01427857ff7
     * parkingId : 15b721f16ae14d0fa519407de1096a10
     * parkingSpaceName : b05
     * isNull : true
     * isOrder : false
     * type : 1
     * createTime : 2021-05-01T12:12:56.000+08:00
     * updateTime : 2021-05-03T12:48:56.000+08:00
     */

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
        private String id;
        private String parkingId;
        private String parkingSpaceName;
        private String isNull;
        private String isOrder;
        private int type;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParkingId() {
            return parkingId;
        }

        public void setParkingId(String parkingId) {
            this.parkingId = parkingId;
        }

        public String getParkingSpaceName() {
            return parkingSpaceName;
        }

        public void setParkingSpaceName(String parkingSpaceName) {
            this.parkingSpaceName = parkingSpaceName;
        }

        public String getIsNull() {
            return isNull;
        }

        public void setIsNull(String isNull) {
            this.isNull = isNull;
        }

        public String getIsOrder() {
            return isOrder;
        }

        public void setIsOrder(String isOrder) {
            this.isOrder = isOrder;
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
