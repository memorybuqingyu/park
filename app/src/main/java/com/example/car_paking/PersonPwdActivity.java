package com.example.car_paking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.car_paking.bean.RePwdBean;
import com.example.car_paking.net.App;
import com.example.car_paking.net.Ok;

/**
 * 修改密码
 */
public class PersonPwdActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private RePwdBean bean;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private Button btn;
    private String old;
    private String new1;
    private String new2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_pwd);

        initView();
    }

    private void initData() {//发送密码修改请求
        new Thread(() -> {
            try {
                bean = Ok.getPut("/user/password", "{ \n" +
                        "\"id\":\"" + App.userId + "\",\n" +
                        "\"password\":\"" + old + "\",\n" +
                        "\"newPas\":\"" + new1 + "\"\n" +
                        "}", RePwdBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean.getCode() == 200) {
                    Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    public void back(View view) {
        //返回上一页
        finish();
    }

    private void initView() {
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit3 = (EditText) findViewById(R.id.edit3);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(v -> {
            old = edit1.getText().toString().trim();
            new1 = edit2.getText().toString().trim();
            new2 = edit3.getText().toString().trim();

            if (old.equals("") || new1.equals("") || new2.equals("")) {
                Toast.makeText(this, "内容不能为空！", Toast.LENGTH_SHORT).show();
            } else {
                if (new1.equals(new2)) {
                    initData();
                } else {
                    Toast.makeText(this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}