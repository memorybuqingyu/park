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
 * 立即停车
 */
public class ParkingNowActivity extends AppCompatActivity {

    private EditText nowEdit;
    private Button nowBtn;
    private Handler handler = new Handler();
    private ParkNowBean bean1;
    private String id;
    private String name;
    private ParkNowBean bean2;
    private String trim;
    private String id1;
    private Pattern pattern = Pattern.compile("^[京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新][ABCDEFGHJKLMNPQRSTUVWXY][\\dABCDEFGHJKLNMxPQRSTUVWXYZ]{5}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_now);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        id1 = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        initView();
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        nowEdit = (EditText) findViewById(R.id.now_edit);
        nowBtn = (Button) findViewById(R.id.now_btn);

        nowBtn.setOnClickListener(v -> {
            trim = nowEdit.getText().toString().trim();
            if (trim.equals("")) {
                Toast.makeText(this, "车牌号不能为空！", Toast.LENGTH_SHORT).show();
            } else {
                if (pattern.matcher(trim).find()){
                    initData2();
                }else {
                    Toast.makeText(this, "请输入正确的车牌号！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData2() {
        new Thread(() -> {

            try {
                bean2 = Ok.getPost("/record", "{\n" +
                        "\"userId\":\"" + App.userId + "\",\n" +
                        "\"parkingSpaceId\":\"" + id + "\",\n" +
                        "\"plateNum\":\"" + trim + "\"\n" +
                        "}", ParkNowBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean2 == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean2.getCode() == 200) {
                    Toast.makeText(this, "停车成功！", Toast.LENGTH_SHORT).show();
                    initData1();
                    finish();
                } else {
                    Toast.makeText(this, "服务器繁忙，请稍后重试！", Toast.LENGTH_SHORT).show();
                }
            });

        }).start();
    }

    private void initData1() {
        new Thread(() -> {

            try {
                bean1 = Ok.getPut("/parkingSpace", "{\n" +
                        "\"id\":\"" + id + "\",\n" +
                        "\"parkingId\":\"" + id1 + "\",\n" +
                        "\"parkingSpaceName\":\"" + name + "\",\n" +
                        "\"isNull\":\"true\",\n" +
                        "\"isOrder\":\"false\"\n" +
                        "}", ParkNowBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean1 == null) {
                Ok.toast(handler, this);
                return;
            }
        }).start();
    }
}