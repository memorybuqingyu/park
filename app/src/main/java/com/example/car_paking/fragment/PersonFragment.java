package com.example.car_paking.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.car_paking.LoginActivity;
import com.example.car_paking.PersonInfoActivity;
import com.example.car_paking.PersonOrderActivity;
import com.example.car_paking.PersonPwdActivity;
import com.example.car_paking.PersonRecordActivity;
import com.example.car_paking.R;
import com.example.car_paking.bean.UserInfoBean;
import com.example.car_paking.net.Ok;

/**
 * 个人中心
 */
public class PersonFragment extends Fragment {
    private TextView personTv1;
    private TextView personTv2;
    private Handler handler = new Handler();
    private UserInfoBean bean1;
    private Context context;
    private TextView personTv3;
    private ImageView personImg;
    private TextView personTv4;
    private TextView personTv5;
    private Button personBtn;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.person_fragment, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {//初始化界面控件
        personTv1 = (TextView) view.findViewById(R.id.person_tv1);
        personTv2 = (TextView) view.findViewById(R.id.person_tv2);
        personTv3 = (TextView) view.findViewById(R.id.person_tv3);
        personImg = (ImageView) view.findViewById(R.id.person_img);
        personTv4 = (TextView) view.findViewById(R.id.person_tv4);
        personTv5 = (TextView) view.findViewById(R.id.person_tv5);
        personBtn = (Button) view.findViewById(R.id.person_btn);


        initTable();
        initData1();
    }

    private void initTable() {//跳转界面

        personBtn.setOnClickListener(new View.OnClickListener() {//退出登录
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        personTv5.setOnClickListener(v -> {//我的预约
            Intent intent = new Intent(context, PersonOrderActivity.class);
            startActivity(intent);
        });

        personTv4.setOnClickListener(v -> {//停车记录
            Intent intent = new Intent(context, PersonRecordActivity.class);
            startActivity(intent);
        });

        personTv2.setOnClickListener(v -> {//个人中心
            Intent intent = new Intent(context, PersonInfoActivity.class);
            startActivity(intent);
        });

        personTv3.setOnClickListener(v -> {//修改密码
            Intent intent = new Intent(context, PersonPwdActivity.class);
            startActivity(intent);
        });
    }

    private void initData1() {//初始化头像和用户名
        new Thread(() -> {

            try {
                bean1 = Ok.getGet("/user", UserInfoBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bean1 == null || bean1.getCode() != 200) {
                Ok.toast(handler, context);
                return;
            }

            UserInfoBean.DataBean users = bean1.getData();

            handler.post(() -> {
                Glide.with(context).load(users.getAvatar()).into(personImg);
                personTv1.setText(users.getUserName());
            });
        }).start();
    }
}
