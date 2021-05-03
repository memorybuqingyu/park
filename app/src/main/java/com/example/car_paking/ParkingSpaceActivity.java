package com.example.car_paking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_paking.adapter.CarAdapter2;
import com.example.car_paking.bean.Park1Bean;
import com.example.car_paking.bean.ParkingBean2;
import com.example.car_paking.inter.MyOnclick;
import com.example.car_paking.net.Ok;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车位
 */
public class ParkingSpaceActivity extends AppCompatActivity {

    private Park1Bean bean = new Park1Bean();
    private String km;
    private Handler handler = new Handler();
    private ParkingBean2 bean1;
    private List<ParkingBean2.DataBean> list1 = new ArrayList<>();
    private RecyclerView park2Rec;
    private String money;
    private String id;

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_space);
        Intent intent = getIntent();
        km = intent.getStringExtra("km");
        money = intent.getStringExtra("money");
        id = intent.getStringExtra("id");
        bean.setKm(km + "公里");
        bean.setMoney(money + "元/小时");

        initView();
    }

    private void initView() {
        park2Rec = (RecyclerView) findViewById(R.id.park2_rec);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        park2Rec.setItemAnimator(new DefaultItemAnimator());
        park2Rec.setLayoutManager(manager);

        initData1();
    }

    private void initData1() {
        new Thread(() -> {

            try {
                bean1 = Ok.getGet("/parkingSpace/parking/" + id, ParkingBean2.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean1 == null || bean1.getCode() != 200) {
                Ok.toast(handler, this);
                return;
            }

            list1 = bean1.getData();
            List<ParkingBean2.DataBean> list1s = new ArrayList<>();
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).getIsNull().equals("false")) {
                    if (list1.get(i).getIsOrder().equals("false")) {
                        list1s.add(list1.get(i));
                    }
                }
            }

            handler.post(() -> {
                bean.setNumber(list1s.size() + "/" + list1.size());

                park2Rec.setAdapter(new CarAdapter2(list1s, bean, this, (MyOnclick) position -> {

                }, position -> {
                    Intent intent = new Intent(this, ParkingNowActivity.class);
                    intent.putExtra("ID", list1s.get(position).getId());
                    intent.putExtra("id", list1s.get(position).getParkingId());
                    intent.putExtra("name", list1s.get(position).getParkingSpaceName());
                    startActivity(intent);
                }, position -> {
                    Intent intent = new Intent(this, ParkingOrderActivity.class);
                    intent.putExtra("id1", list1s.get(position).getId());
                    intent.putExtra("id2", list1s.get(position).getParkingId());
                    intent.putExtra("name", list1s.get(position).getParkingSpaceName());
                    startActivity(intent);
                }));
            });
        }).start();
    }

    public void back(View view) {
        finish();
    }


}