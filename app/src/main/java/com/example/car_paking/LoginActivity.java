package com.example.car_paking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.car_paking.bean.LoginBean;
import com.example.car_paking.bean.UserInfoBean;
import com.example.car_paking.net.App;
import com.example.car_paking.net.Ok;

import java.io.InputStream;

/**
 * 登录类
 */
public class LoginActivity extends AppCompatActivity {

    private EditText loginEditUser;
    private EditText loginEditPwd;
    private TextView loginTvRegister;
    private Handler handler = new Handler();
    private Button loginBtn;
    private String user;
    private String pwd;
    private ImageView loginImg;
    private TextView loginTvMa;
    private ImageView imageView6;
    private EditText loginEditMa;
    private LoginBean bean;
    private String ma;
    private UserInfoBean bean1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {//初始化控件
        loginEditUser = (EditText) findViewById(R.id.login_edit_user);
        loginEditPwd = (EditText) findViewById(R.id.login_edit_pwd);
        loginTvRegister = (TextView) findViewById(R.id.login_tv_register);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginImg = (ImageView) findViewById(R.id.login_img);
        loginTvMa = (TextView) findViewById(R.id.login_tv_ma);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        loginEditMa = (EditText) findViewById(R.id.login_edit_ma);

        loginTvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        initImg();//初始化验证码

        //登录按钮
        loginBtn.setOnClickListener(v -> {
            user = loginEditUser.getText().toString().trim();
            pwd = loginEditPwd.getText().toString().trim();
            ma = loginEditMa.getText().toString().trim();
            if (!user.equals("")) {//判断是否输入为空
                if (!pwd.equals("")) {

                    if (!ma.equals("")) {
                        Log.d("TAG", "initView: "+ma);
                        Log.d("TAG", "initView: "+ma.equals(""));
                        initData();
                    } else {
                        Toast.makeText(this, "验证码不能为空！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            }
        });
        loginTvMa.setOnClickListener(v -> initImg());//点击切换验证码
    }

    private void initImg() {//加载图片验证码
        new Thread(() -> {
            try {
                InputStream stream = Ok.getData2("/user/captcha");
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                loginImg.post(() -> loginImg.setImageBitmap(bitmap));
            } catch (Exception e) {
                Ok.toast(handler, this);
                return;
            }
        }).start();
    }

    private void initData() {//发送登录请求
        new Thread(() -> {
            try {
                bean = Ok.getPost("/user/login/captcha?captchaCode=" + ma, "{\n" +
                        "\"userName\":\"" + user + "\",\n" +
                        "\"password\":\"" + pwd + "\"\n" +
                        "}", LoginBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean.getCode() == 200) {
                    Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
                    App.token = bean.getData();
                    initData1();
                } else {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void initData1() {//获取用户信息，用于判断用户身份{
        new Thread(() -> {

            try {
                bean1 = Ok.getGet("/user", UserInfoBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean1 == null || bean1.getCode() != 200) {
                Ok.toast(handler, this);
                return;
            }

            UserInfoBean.DataBean users = bean1.getData();

            handler.post(() -> {
                App.userId = users.getId();
                if (users.getState().equals("1")) {//普通用户
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (users.getState().equals("2")) {//管理员

                }
            });
        }).start();
    }
}