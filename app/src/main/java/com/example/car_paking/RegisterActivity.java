package com.example.car_paking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.car_paking.bean.EmailBean;
import com.example.car_paking.bean.RegisterBean;
import com.example.car_paking.net.Ok;

import java.io.InputStream;

/**
 * 注册类
 */
public class RegisterActivity extends AppCompatActivity {

    private TextView registerTvLogin;
    private Button registerBtn;
    private EditText registerEdit6;
    private EditText registerEdit1;
    private EditText registerEdit2;
    private EditText registerEdit3;
    private Button registerBtnMa;
    private EditText registerEdit4;
    private EditText registerEdit5;
    private ImageView registerImg;
    private TextView registerTvMa;
    private String email;
    private EmailBean bean1;
    private Handler handler = new Handler();
    private String username;
    private String pwd;
    private String email1;
    private String ma1;
    private String phone;
    private String ma2;
    private RegisterBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {//初始化界面控件
        registerTvLogin = (TextView) findViewById(R.id.register_tv_login);
        registerBtn = (Button) findViewById(R.id.register_btn);

        registerTvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        registerEdit1 = (EditText) findViewById(R.id.register_edit1);
        registerEdit2 = (EditText) findViewById(R.id.register_edit2);
        registerEdit3 = (EditText) findViewById(R.id.register_edit3);
        registerEdit4 = (EditText) findViewById(R.id.register_edit4);
        registerEdit5 = (EditText) findViewById(R.id.register_edit5);
        registerEdit6 = (EditText) findViewById(R.id.register_edit6);

        registerBtnMa = (Button) findViewById(R.id.register_btn_ma);
        registerImg = (ImageView) findViewById(R.id.register_img);
        registerTvMa = (TextView) findViewById(R.id.register_tv_ma);


        initTable();
        initImg();

        registerTvMa.setOnClickListener(v -> {//用于切换验证码
            initImg();
        });
    }

    private void initImg() {//加载图片验证码
        new Thread(() -> {
            try {
                InputStream stream = Ok.getData2("/user/captcha");
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                registerImg.post(() -> registerImg.setImageBitmap(bitmap));
            } catch (Exception e) {
                Ok.toast(handler, this);
                return;
            }
        }).start();
    }

    private void initTable() {

        registerBtnMa.setOnClickListener(v -> {//获取邮箱验证码
            email = registerEdit3.getText().toString().trim();
            if (email.equals("")) {
                Toast.makeText(this, "邮箱不能为空！", Toast.LENGTH_SHORT).show();
            } else {
                initData1(email);
            }
        });

        registerBtn.setOnClickListener(v -> {//提交注册请求
            username = registerEdit1.getText().toString().trim();
            pwd = registerEdit2.getText().toString().trim();
            email1 = registerEdit3.getText().toString().trim();
            ma1 = registerEdit4.getText().toString().trim();
            phone = registerEdit5.getText().toString().trim();
            ma2 = registerEdit6.getText().toString().trim();

            if (username.equals("") || pwd.equals("") || email1.equals("") || ma1.equals("") || ma1.equals("") || phone.equals("") || ma2.equals("")) {
                Toast.makeText(this, "内容不能为空！", Toast.LENGTH_SHORT).show();
            } else {
                if (phone.length() == 11) {
                    initData();
                } else {
                    Toast.makeText(this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData1(String email) {//用于发送邮箱验证码请求
        new Thread(() -> {
            try {
                bean1 = Ok.getGet("/user/verify_code?email=" + email + "&type=register", EmailBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean1 == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean1.getCode() == 200) {
                    Toast.makeText(this, "获取成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,bean1.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();

    }

    private void initData() {//发送注册请求
        new Thread(() -> {
            try {
                bean = Ok.getPost("/user/register?emailCode=" + ma1 + "&captchaCode=" + ma2, "{\n" +
                        "\"userName\":\"" + username + "\",\n" +
                        "\"password\":\"" + pwd + "\",\n" +
                        "\"email\":\"" + email1 + "\",\n" +
                        "\"phone\":\"" + phone + "\"\n" +
                        "}", RegisterBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean.getCode() == 200) {
                    Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}