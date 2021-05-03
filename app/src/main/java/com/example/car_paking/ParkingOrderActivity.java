package com.example.car_paking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.car_paking.bean.ParkNowBean;
import com.example.car_paking.net.App;
import com.example.car_paking.net.Ok;

import java.util.regex.Pattern;

/**
 * 预约停车
 */
public class ParkingOrderActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private ParkNowBean bean;
    private String id1;
    private EditText nowEdit;
    private Button nowBtn;
    private String trim;
    private String name;
    private ParkNowBean bean2;
    private String id2;
    private Pattern pattern = Pattern.compile("^[京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新][ABCDEFGHJKLMNPQRSTUVWXY][\\dABCDEFGHJKLNMxPQRSTUVWXYZ]{5}$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_order);
        Intent intent = getIntent();
        id1 = intent.getStringExtra("id1");
        id2 = intent.getStringExtra("id2");
        name = intent.getStringExtra("name");

        initView();
    }

    private void initView() {
        nowEdit = (EditText) findViewById(R.id.now_edit);
        nowBtn = (Button) findViewById(R.id.now_btn);

        nowBtn.setOnClickListener(v -> {
            trim = nowEdit.getText().toString().trim();

            if (trim.equals("")) {
                Toast.makeText(this, "内容不能为空!", Toast.LENGTH_SHORT).show();
            } else {
                boolean b = pattern.matcher(trim).find();
                if (b) {
                    initData1();
                } else {
                    Toast.makeText(this, "请输入正确的车牌号！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData2() {
        new Thread(() -> {

            try {
                bean2 = Ok.getPut("/parkingSpace", "{\n" +
                        "\"id\":\"" + id1 + "\",\n" +
                        "\"parkingId\":\"" + id2 + "\",\n" +
                        "\"parkingSpaceName\":\"" + name + "\",\n" +
                        "\"isNull\":\"false\",\n" +
                        "\"isOrder\":\"true\"\n" +
                        "}", ParkNowBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean2 == null || bean2.getCode() != 200) {
                Ok.toast(handler, this);
                return;
            }
        }).start();
    }

    private void initData1() {
        new Thread(() -> {

            try {
                bean = Ok.getPost("/parkingOrder", "{\n" +
                        "\"userId\":\"" + App.userId + "\",\n" +
                        "\"parkingSpaceId\":\"" + id1 + "\",\n" +
                        "\"plateNum\":\"" + trim + "\"\n" +
                        "}", ParkNowBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean.getCode() == 200) {
                    Toast.makeText(this, "预约成功,请在十分钟内前往停车！", Toast.LENGTH_LONG).show();
                    initData2();
                    finish();
                } else {
                    Toast.makeText(this, "服务器繁忙，请稍后重试！", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    public void back(View view) {
        finish();
    }
}