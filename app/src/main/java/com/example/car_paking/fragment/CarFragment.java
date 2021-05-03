package com.example.car_paking.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_paking.ParkingNowActivity;
import com.example.car_paking.ParkingSpaceActivity;
import com.example.car_paking.R;
import com.example.car_paking.adapter.CarAdapter1;
import com.example.car_paking.adapter.HomeBannerAdapter;
import com.example.car_paking.bean.Park1Bean;
import com.example.car_paking.bean.ParkingBean;
import com.example.car_paking.inter.MyOnclick;
import com.example.car_paking.net.Ok;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import org.xml.sax.helpers.ParserAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * 首页
 */
public class CarFragment extends Fragment {

    private Banner homeBanner;
    private EditText homeEdit;
    private List<Integer> list = new ArrayList<>();
    private Context context;
    private Handler handler = new Handler();
    private ParkingBean bean1;
    private RecyclerView park1Rec;
    private ImageView parkImg;

    private List<ParkingBean.DataBean> list1 = new ArrayList<>();
    private ParkingBean bean2;
    private List<ParkingBean.DataBean> list2 = new ArrayList<>();
    private String name;
    private ImageView img;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 & resultCode == -1) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.car_fragment, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {//初始化控件
        homeBanner = (Banner) view.findViewById(R.id.home_banner);
        homeEdit = (EditText) view.findViewById(R.id.home_edit);
        homeEdit = (EditText) view.findViewById(R.id.home_edit);
        park1Rec = (RecyclerView) view.findViewById(R.id.park1_rec);
        parkImg = (ImageView) view.findViewById(R.id.park_img);

        parkImg.setOnClickListener(v -> {
            //弹出二维码
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view1 = LayoutInflater.from(context).inflate(R.layout.dialog, null);
            AlertDialog dialog = builder.setView(view1).create();
            dialog.show();
            img = view1.findViewById(R.id.dialog_img);
            initQCode();//调用二维码

            img.setOnLongClickListener(v1 -> {//长按二维码跳转
                Intent intent = new Intent(context, ParkingNowActivity.class);

                intent.putExtra("ID", "e842e2cc8ef24285a598880d4105c697");
                intent.putExtra("id", "15b721f16ae14d0fa519407de1096a10");
                intent.putExtra("name", "C07");
                startActivity(intent);
                dialog.dismiss();
                return true;
            });
        });


        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        park1Rec.setItemAnimator(new DefaultItemAnimator());
        park1Rec.setLayoutManager(manager);

        Collections.addAll(list, R.mipmap.paking1, R.mipmap.paking2, R.mipmap.paking3, R.mipmap.paking4, R.mipmap.paking5);
        initBanner();
        initData1();


        homeEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                name = homeEdit.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(context, "未输入内容查询全部停车场！", Toast.LENGTH_SHORT).show();
                    initData1();
                } else {
                    initData2();
                }
            }
            return true;
        });
    }

    private void initQCode() {//初始化二维码

        img.post(() -> {
            try {
                int imgWidth = img.getWidth();
                int imgHeight = img.getHeight();
                Hashtable<EncodeHintType, String> hints = new Hashtable<>();
                //设置字符集
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                BitMatrix bitMatrix = new QRCodeWriter().encode("123", BarcodeFormat.QR_CODE, imgWidth, imgHeight);
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

    private void initData2() {//模糊查询停车场
        new Thread(() -> {

            try {
                bean2 = Ok.getGet("/parking/name?parkingName=" + name, ParkingBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean2 == null || bean2.getCode() != 200) {
                Ok.toast(handler, context);
                return;
            }

            list2 = bean2.getData();

            handler.post(() -> {
                if (list2.size() == 0) {
                    Toast.makeText(context, "未查询到相关停车场！", Toast.LENGTH_SHORT).show();
                } else {
                    park1Rec.setAdapter(new CarAdapter1(list2, context, position -> {
                        Intent intent = new Intent(context, ParkingSpaceActivity.class);
                        intent.putExtra("km", position + 10 + "");
                        intent.putExtra("money", list2.get(position).getPrice() + "");
                        intent.putExtra("id", list2.get(position).getId() + "");
                        startActivity(intent);
                    }));
                }
            });
        }).start();
    }

    private void initData1() {//查询全部停车场
        new Thread(() -> {

            try {
                bean1 = Ok.getGet("/parking/list", ParkingBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean1 == null || bean1.getCode() != 200) {
                Ok.toast(handler, context);
                return;
            }

            list1 = bean1.getData();

            handler.post(() -> {
                park1Rec.setAdapter(new CarAdapter1(list1, context, position -> {
                    Intent intent = new Intent(context, ParkingSpaceActivity.class);
                    intent.putExtra("km", position + 10 + "");
                    intent.putExtra("money", list1.get(position).getPrice() + "");
                    intent.putExtra("id", list1.get(position).getId() + "");
                    startActivity(intent);
                }));
            });
        }).start();
    }

    private void initBanner() {//设置首页轮播图
        homeBanner.setAdapter(new HomeBannerAdapter(list), true).setIndicator(new CircleIndicator(context));
        homeBanner.isAutoLoop(true);
        homeBanner.setIndicatorNormalColor(Color.WHITE);
        homeBanner.setIndicatorSelectedColor(Color.RED);
        homeBanner.setIndicatorSpace(30);
    }
}
