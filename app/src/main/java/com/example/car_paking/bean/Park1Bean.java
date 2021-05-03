package com.example.car_paking.bean;

/**
 * 车位信息类
 */
public class Park1Bean {
    private String km;
    private String money;
    private String number;

    public Park1Bean() {
    }

    public Park1Bean(String km, String money, String number) {
        this.km = km;
        this.money = money;
        this.number = number;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
