package com.example.car_paking.net;

import android.app.Application;

//用于存储全局数据
public class App extends Application {

    public static String userId = "";
    public static String token = "";
    public static String addDress = "";
    public static String km = "";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
