package com.example.car_paking;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.car_paking.fragment.CarFragment;
import com.example.car_paking.fragment.PersonFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * 初始化底部导航栏，点击切换
 */
public class HomeActivity extends AppCompatActivity {

    private TextView homeTop;
    private BottomNavigationView homeBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }

    private void initFragment(Fragment fragment) {//用于切换底部导航方法
        getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, fragment).commit();
    }

    private void initView() {
        homeTop = (TextView) findViewById(R.id.home_top);
        homeBottom = (BottomNavigationView) findViewById(R.id.home_bottom);

        initFragment(new CarFragment());
        //初始化底部导航
        homeBottom.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.it1:
                    initFragment(new CarFragment());
                    homeTop.setText("首页");
                    break;
                case R.id.it2:
                    initFragment(new PersonFragment());
                    homeTop.setText("个人中心");
                    break;
            }
            return true;
        });
    }
}