package com.example.car_paking;

import android.graphics.Bitmap;
import android.net.MacAddress;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_paking.adapter.PersonReAdapter;
import com.example.car_paking.bean.InfoSuccessBean;
import com.example.car_paking.bean.ParkRecordBean2;
import com.example.car_paking.bean.ParkingBean;
import com.example.car_paking.bean.ParkingBean2;
import com.example.car_paking.bean.ParkingRecordBean;
import com.example.car_paking.bean.RecordBean;
import com.example.car_paking.inter.MyOnclick;
import com.example.car_paking.net.App;
import com.example.car_paking.net.Ok;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * 停车记录
 */
public class PersonRecordActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private RecordBean bean1;
    private List<RecordBean.DataBean> list1 = new ArrayList<>();
    private RecyclerView recordRec;
    private ParkingRecordBean bean2;
    private ParkRecordBean2 bean3;
    private List<ParkingRecordBean> list2 = new ArrayList<>();
    private List<ParkRecordBean2> list3 = new ArrayList<>();
    private Date parse;
    private SimpleDateFormat simpleDateFormat;
    private InfoSuccessBean beans;
    private ImageView img;
    private double money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_record);

        initView();
    }

    private void initView() {
        recordRec = (RecyclerView) findViewById(R.id.record_rec);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recordRec.setLayoutManager(manager);
        recordRec.setItemAnimator(new DefaultItemAnimator());

        initData1();
    }

    private void initData1() {//初始化界面数据
        new Thread(() -> {

            try {
                bean1 = Ok.getGet("/record/user/" + App.userId, RecordBean.class);

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

            if (list2.size() == 0) {
                Ok.toast(handler, this);
                return;
            }


            for (int i = 0; i < list2.size(); i++) {
                try {
                    ParkRecordBean2 bea3 = Ok.getGet("/parking/" + list2.get(i).getData().getParkingId(), ParkRecordBean2.class);
                    list3.add(bea3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (list3.size() == 0) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                recordRec.setAdapter(new PersonReAdapter(list1, list3, this, position -> {//设置数据
                    String id = list1.get(position).getId();
                    String time = list1.get(position).getCreateTime().replace("T", " ");
                    try {
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        parse = simpleDateFormat.parse(time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date = new Date();
                    long time1 = date.getTime();
                    long time2 = parse.getTime();
                    long times = time1 - time2;
                    long in = times / 1000 / 60 / 60;
                    money = 0.0;
                    if (in < 1) {
                        money = list3.get(position).getData().getPrice();
                    } else {
                        money = list3.get(position).getData().getPrice() * in;
                    }

                    //弹出二维码
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
                    AlertDialog dialog = builder.setView(view).create();
                    dialog.show();
                    img = view.findViewById(R.id.dialog_img);

                    img();//调用二维码生成方法

                    double finalMoney = money;
                    img.setOnLongClickListener(v -> {//长按二维码完成付款并出库
                        initData2(id, finalMoney);
                        dialog.dismiss();
                        return true;
                    });
                }));
            });

        }).start();
    }

    private void initData2(String id, Double money) {//车辆车库方法
        new Thread(() -> {
            try {
                beans = Ok.getPut("/record", "{\n" +
                        "\"id\":\"" + id + "\",\n" +
                        "\"type\":\"2\",\n" +
                        "\"money\":" + money + "\n" +
                        "}", InfoSuccessBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (beans == null) {
                Ok.toast(handler, this);
                return;
            }

            handler.post(() -> {
                if (beans.getCode() == 200) {
                    Toast.makeText(this, "出库成功！", Toast.LENGTH_SHORT).show();
                    initData1();
                }
            });
        }).start();
    }

    private void img() {//生成二维码
        img.post(() -> {
            try {
                int imgWidth = img.getWidth();
                int imgHeight = img.getHeight();
                Hashtable<EncodeHintType, String> hints = new Hashtable<>();
                //设置字符集
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                BitMatrix bitMatrix = new QRCodeWriter().encode(money + "", BarcodeFormat.QR_CODE, imgWidth, imgHeight);
                int[] pixels = new int[imgWidth * imgHeight];
                for (int i = 0; i < imgHeight; i++) {
                    for (int j = 0; j < imgWidth; j++) {
                        if (bitMatrix.get(j, i)) {
                            pixels[i * imgHeight + j] = 0xff000000;
                        } else {
                            pixels[i * imgHeight + j] = 0xffffffff;
                        }
                    }
                }
                //生成二维码图片
                Bitmap bitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, imgWidth, 0, 0, imgWidth, imgHeight);
                img.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });
    }

    public void back(View view) {//返回上一页
        finish();
    }


}