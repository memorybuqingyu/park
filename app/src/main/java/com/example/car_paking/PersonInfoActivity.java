package com.example.car_paking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.car_paking.bean.InfoSuccessBean;
import com.example.car_paking.bean.UserInfoBean;
import com.example.car_paking.net.App;
import com.example.car_paking.net.Ok;

/**
 * 用户信息修改
 */
public class PersonInfoActivity extends AppCompatActivity {

    private Button infoBtn1;
    private EditText infoEdit1;
    private EditText infoEdit2;
    private EditText infoEdit3;
    private Handler handler = new Handler();
    private String it1;
    private String it2;
    private String it3;
    private UserInfoBean bean1;
    private InfoSuccessBean bean2;
    private UserInfoBean.DataBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        initView();
    }

    public void back(View view) {//返回上一页
        finish();
    }

    private void initView() {
        infoBtn1 = (Button) findViewById(R.id.info_btn1);
        infoEdit1 = (EditText) findViewById(R.id.info_edit1);
        infoEdit2 = (EditText) findViewById(R.id.info_edit2);
        infoEdit3 = (EditText) findViewById(R.id.info_edit3);

        initData1();
        infoBtn1.setOnClickListener(v -> {

            it1 = infoEdit1.getText().toString();
            it2 = infoEdit2.getText().toString();
            it3 = infoEdit3.getText().toString();

            if (it1.equals("") || it2.equals("") || it3.equals("")) {
                Toast.makeText(this, "修改内容不能为空！", Toast.LENGTH_SHORT).show();
            } else {
                initData2();
            }
        });
    }

    private void initData2() {
        new Thread(() -> {

            if (user.getPhone().equals(it2)) {
                try {
                    bean2 = Ok.getPut("/user", "{ \n" +
                            "\"id\":\"" + App.userId + "\",\n" +
                            "\"userName\": \"" + it1 + "\",\n" +
                            "\"sign\" : \"" + it3 + "\"\n" +
                            "}", InfoSuccessBean.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    bean2 = Ok.getPut("/user", "{ \n" +
                            "\"id\":\"" + App.userId + "\",\n" +
                            "\"userName\": \"" + it1 + "\",\n" +
                            "\"sign\" : \"" + it3 + "\",\n" +
                            "\"phone\": \"" + it2 + "\"\n" +
                            "}", InfoSuccessBean.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            if (bean2 == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean2.getCode() == 200) {
                    Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "修改失败，请稍后重试！", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void initData1() {
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

            user = bean1.getData();

            handler.post(() -> {
                infoEdit1.setText(user.getUserName());
                infoEdit2.setText(user.getPhone());
                infoEdit3.setText(user.getSign());
            });
        }).start();
    }
}