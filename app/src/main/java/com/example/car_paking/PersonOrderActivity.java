package com.example.car_paking;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_paking.adapter.PersonOrAdapter;
import com.example.car_paking.bean.OrderBean;
import com.example.car_paking.bean.ParkNowBean;
import com.example.car_paking.bean.ParkRecordBean2;
import com.example.car_paking.bean.ParkingBean2;
import com.example.car_paking.bean.ParkingRecordBean;
import com.example.car_paking.bean.PersonOrderBean;
import com.example.car_paking.inter.MyOnclick;
import com.example.car_paking.net.App;
import com.example.car_paking.net.Ok;

import java.util.ArrayList;
import java.util.List;

public class PersonOrderActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private PersonOrderBean bean1;
    private List<PersonOrderBean.DataBean> list1 = new ArrayList<>();
    private List<ParkingRecordBean> list2 = new ArrayList<>();
    private List<ParkRecordBean2> list3 = new ArrayList<>();
    private RecyclerView personOrderRec;
    private ParkNowBean bean3;
    private ParkNowBean bean4;
    private OrderBean bean2;
    private OrderBean bean5;
    private String idas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_order);

        initView();
    }

    private void initView() {
        personOrderRec = (RecyclerView) findViewById(R.id.person_order_rec);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        personOrderRec.setItemAnimator(new DefaultItemAnimator());
        personOrderRec.setLayoutManager(manager);

        initData1();
    }

    private void initData1() {
        new Thread(() -> {
            try {
                bean1 = Ok.getGet("/parkingOrder/user/" + App.userId, PersonOrderBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean1 == null || bean1.getCode() != 200) {
                Ok.toast(handler, this);
                return;
            }

            list1 = bean1.getData();

            for (int i = 0; i < list1.size(); i++) {
                try {
                    ParkingRecordBean bean = Ok.getGet("/parkingSpace/" + list1.get(i).getParkingSpaceId(), ParkingRecordBean.class);
                    list2.add(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < list2.size(); i++) {
                try {
                    ParkRecordBean2 bea3 = Ok.getGet("/parking/" + list2.get(i).getData().getParkingId(), ParkRecordBean2.class);
                    list3.add(bea3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            handler.post(() -> {
                personOrderRec.setAdapter(new PersonOrAdapter(list1, list3, this, position -> {
                    String id = list1.get(position).getId();
                    Log.i("TAG", "initData1: --" + id);
                    initData2(id);
                }, position -> {
                    idas = list1.get(position).getId();
                    String parkingId = list1.get(position).getParkingSpaceId();
                    String plateNum = list1.get(position).getPlateNum();
                    String spaceId = list2.get(position).getData().getId();
                    String name = list2.get(position).getData().getParkingSpaceName();
                    initData3(spaceId,plateNum,parkingId,name);
                }));
            });
        }).start();
    }

    private void initData2(String id) {
        new Thread(() -> {

            try {
                Log.d("TAG", "initData2: "+id);
                bean2 = Ok.getDelete("/parkingOrder/" + id, OrderBean.class);
                Log.d("TAG", "initData2: "+bean2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bean2 == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean2.getCode() == 200) {
                    Toast.makeText(this, "取消成功!", Toast.LENGTH_SHORT).show();
                    initData1();
                } else {
                    Toast.makeText(this, "服务器繁忙请稍后重试！", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void initData3(String spaceId, String num,String parkId,String name) {
        new Thread(() -> {
            try {
                bean3 = Ok.getPost("/record", "{\n" +
                        "\"userId\":\"" + App.userId + "\",\n" +
                        "\"parkingSpaceId\":\"" + spaceId + "\",\n" +
                        "\"plateNum\":\"" + num + "\"\n" +
                        "}", ParkNowBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean3 == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (bean3.getCode() == 200) {
                    Toast.makeText(this, "停车成功！", Toast.LENGTH_SHORT).show();
                    initData4(spaceId,parkId,name);
                } else {
                    Toast.makeText(this, "服务器繁忙，请稍后重试！", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void initData4(String parkingId,String id,String name) {
        new Thread(() -> {

            try {
                bean4 = Ok.getPut("/parkingSpace", "{\n" +
                        "\"id\":\"" + id + "\",\n" +
                        "\"parkingId\":\"" + parkingId + "\",\n" +
                        "\"parkingSpaceName\":\"" + name + "\",\n" +
                        "\"isNull\":\"true\",\n" +
                        "\"isOrder\":\"false\"\n" +
                        "}", ParkNowBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean4 == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(()->{
               initData5(idas);
            });
        }).start();
    }

    private void initData5(String id) {
        new Thread(()->{

            try {
                bean5 = Ok.getPut("/parkingOrder", "{\n" +
                        "\"id\":\"" + id + "\",\n" +
                        "\"type\":\"2\"\n" +
                        "}", OrderBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean5 == null || bean5.getCode()!= 200){
                Ok.toast(handler,this);
                return;
            }
            handler.post(()->{
               initData1();
            });
        }).start();
    }

    public void back(View view) {
        finish();
    }


}