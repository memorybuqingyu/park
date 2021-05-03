package com.example.car_paking.bean;

public class ParkRecordBean2 {

    /**
     * code : 200
     * message : 请求成功
     * success : false
     * data : {"id":"15b721f16ae14d0fa519407de1096a10","parkName":"二号停车场","price":4,"priceCap":40,"allPark":20,"vacancy":0,"imgUrl":"/parkingImg/tcc2.jpg","createTime":"2021-05-01T11:32:56.000+08:00","updateTime":"2021-05-01T11:32:56.000+08:00","type":1}
     */

    private int code;
    private String message;
    private boolean success;
    /**
     * id : 15b721f16ae14d0fa519407de1096a10
     * parkName : 二号停车场
     * price : 4.0
     * priceCap : 40.0
     * allPark : 20
     * vacancy : 0
     * imgUrl : /parkingImg/tcc2.jpg
     * createTime : 2021-05-01T11:32:56.000+08:00
     * updateTime : 2021-05-01T11:32:56.000+08:00
     * type : 1
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
        private String parkName;
        private double price;
        private double priceCap;
        private int allPark;
        private int vacancy;
        private String imgUrl;
        private String createTime;
        private String updateTime;
        private int type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParkName() {
            return parkName;
        }

        public void setParkName(String parkName) {
            this.parkName = parkName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPriceCap() {
            return priceCap;
        }

        public void setPriceCap(double priceCap) {
            this.priceCap = priceCap;
        }

        public int getAllPark() {
            return allPark;
        }

        public void setAllPark(int allPark) {
            this.allPark = allPark;
        }

        public int getVacancy() {
            return vacancy;
        }

        public void setVacancy(int vacancy) {
            this.vacancy = vacancy;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
