package com.example.car_paking.net;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//网络请求工具类
public class Ok {
    //服务器地址
    public static String URL = "http://192.168.3.43:8080";
    //使用okHttp3访问
    private static OkHttpClient client = new OkHttpClient();

    //post请求
    public static String getData(String url, String json) throws Exception {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(URL + url)
                .method("POST", body)
                .addHeader("Authorization", App.token)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //get请求
    public static String getData(String url) throws Exception {
        Request request = new Request.Builder()
                .url(URL + url)
                .method("GET", null)
                .addHeader("Authorization", App.token)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //delete请求
    public static String getDataa(String url) throws Exception {
        Request request = new Request.Builder()
                .url(URL + url)
                .method("DELETE", null)
                .addHeader("Authorization", App.token)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //加载验证码
    public static InputStream getData2(String url) throws Exception {
        Request request = new Request.Builder()
                .url(URL + url)
                .method("GET", null)
                .addHeader("Authorization", App.token)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().byteStream();
    }

    //put请求
    private static String getPut(String url, String json) throws Exception {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(URL + url)
                .method("PUT", body)
                .addHeader("Authorization", App.token)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //解析post请求返回json数据
    public static <T> T getPost(String url, String json, Class<T> tClass) throws Exception {
        String data = getData(url, json);
        Log.i("TAG", "getPost: --------->" + data);
        return new Gson().fromJson(data, tClass);
    }

    //解析put请求返回json数据
    public static <T> T getPut(String url, String json, Class<T> tClass) throws Exception {
        String data = getPut(url, json);
        Log.i("TAG", "getPut: --------->" + data);
        return new Gson().fromJson(data, tClass);
    }

    //解析get请求返回json数据
    public static <T> T getGet(String url, Class<T> tClass) throws Exception {
        String data = getData(url);
        Log.i("TAG", "getGet: --------->" + data);
        return new Gson().fromJson(data, tClass);
    }

    //解析delete请求返回json数据
    public static <T> T getDelete(String url, Class<T> tClass) throws Exception {
        String data = getDataa(url);
        Log.i("TAG", "getDelete: --------->" + data);
        return new Gson().fromJson(data, tClass);
    }

    //用于网络错误时处理
    public static void toast(Handler handler, Context context) {
        handler.post(() -> Toast.makeText(context, "服务出错了！", Toast.LENGTH_SHORT).show());
    }
}
