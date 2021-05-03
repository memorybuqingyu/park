package com.example.car_paking.bean;

import java.util.List;

public class ParkingBean {

    /**
     * code : 200
     * message : 请求成功
     * success : false
     * data : [{"id":"15b721f16ae14d0fa519407de1096a10","parkName":"二号停车场","price":4,"priceCap":40,"allPark":20,"vacancy":0,"imgUrl":"/parkingImg/tcc2.jpg","createTime":"2021-05-01T11:32:56.000+00:00","updateTime":"2021-05-01T11:32:56.000+00:00","type":1},{"id":"299d1c4632db4b9f96f07e5772240d03","parkName":"七号停车场","price":3,"priceCap":20,"allPark":10,"vacancy":0,"imgUrl":"/parkingImg/tcc7.jpg","createTime":"2021-05-01T11:34:20.000+00:00","updateTime":"2021-05-01T11:34:20.000+00:00","type":1},{"id":"32025e931e954a269224e2b350416e4a","parkName":"五号停车场","price":6,"priceCap":30,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc5.jpg","createTime":"2021-05-01T11:33:56.000+00:00","updateTime":"2021-05-01T11:33:56.000+00:00","type":1},{"id":"54d6b097e59840a3832caf3630c57eda","parkName":"十一号停车场","price":5,"priceCap":50,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc11.jpg","createTime":"2021-05-01T11:35:38.000+00:00","updateTime":"2021-05-01T11:35:38.000+00:00","type":1},{"id":"5ab864cff2a74ae5ab5dbfb6edb6f2c8","parkName":"十号停车场","price":5,"priceCap":30,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc10.jpg","createTime":"2021-05-01T11:35:20.000+00:00","updateTime":"2021-05-01T11:35:20.000+00:00","type":1},{"id":"b10db8c2a8704996bdbc4f9f65b50181","parkName":"十二号停车场","price":7,"priceCap":26,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc12.jpg","createTime":"2021-05-01T11:35:53.000+00:00","updateTime":"2021-05-01T11:49:31.000+00:00","type":1},{"id":"bbc4cda60a0f42af801b7d7e0dd559fc","parkName":"四号停车场","price":5,"priceCap":20,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc4.jpg","createTime":"2021-05-01T11:33:40.000+00:00","updateTime":"2021-05-01T11:33:40.000+00:00","type":1},{"id":"c755ed50b5a54b2cb16fc6fe1165205a","parkName":"六号停车场","price":3,"priceCap":20,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc6.jpg","createTime":"2021-05-01T11:34:13.000+00:00","updateTime":"2021-05-01T11:34:13.000+00:00","type":1},{"id":"cdf22e1d22c44f0396f69779c86232ab","parkName":"九号停车场","price":6,"priceCap":36,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc9.jpg","createTime":"2021-05-01T11:35:04.000+00:00","updateTime":"2021-05-01T11:35:04.000+00:00","type":1},{"id":"d03fd25a99874a4db1f5ea420f70df56","parkName":"八号停车场","price":4,"priceCap":48,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc8.jpg","createTime":"2021-05-01T11:34:35.000+00:00","updateTime":"2021-05-01T11:34:35.000+00:00","type":1},{"id":"dd6baa686ab14999871642170360c6d2","parkName":"三号停车场","price":5,"priceCap":35,"allPark":5,"vacancy":0,"imgUrl":"/parkingImg/tcc3.jpg","createTime":"2021-05-01T11:33:24.000+00:00","updateTime":"2021-05-01T11:33:24.000+00:00","type":1},{"id":"df6d9869adb34ddb9c4d02e114989098","parkName":"一号停车场","price":5,"priceCap":30,"allPark":20,"vacancy":0,"imgUrl":"/parkingImg/tcc1.jpg","createTime":"2021-05-01T11:31:23.000+00:00","updateTime":"2021-05-01T11:31:23.000+00:00","type":1}]
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
     * createTime : 2021-05-01T11:32:56.000+00:00
     * updateTime : 2021-05-01T11:32:56.000+00:00
     * type : 1
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
