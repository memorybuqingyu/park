package com.example.car_paking.adapter;


import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.List;

/**
 * 首页轮播图适配器
 */
public class HomeBannerAdapter extends BannerImageAdapter<Integer> {

    public HomeBannerAdapter(List<Integer> mData) {
        super(mData);
    }

    @Override
    public void onBindView(BannerImageHolder bannerImageHolder, Integer integer, int i, int i1) {
        bannerImageHolder.imageView.setImageResource(integer);
    }
}
