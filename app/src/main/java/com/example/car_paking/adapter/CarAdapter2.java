package com.example.car_paking.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.car_paking.ParkingNowActivity;
import com.example.car_paking.R;
import com.example.car_paking.bean.Park1Bean;
import com.example.car_paking.bean.ParkingBean;
import com.example.car_paking.bean.ParkingBean2;
import com.example.car_paking.inter.MyOnclick;
import com.example.car_paking.net.Ok;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车场查询适配器
 */
public class CarAdapter2 extends RecyclerView.Adapter<CarAdapter2.MyHolder> {

    private List<ParkingBean2.DataBean> list = new ArrayList<>();
    private Park1Bean bean;
    private Context context;
    private MyOnclick onclick;
    private MyOnclick onclick1;
    private MyOnclick onclick2;

    public CarAdapter2(List<ParkingBean2.DataBean> list, Park1Bean bean, Context context, MyOnclick onclick,MyOnclick onclick1,MyOnclick onclick2) {
        this.list = list;
        this.bean = bean;
        this.context = context;
        this.onclick = onclick;
        this.onclick1 = onclick1;
        this.onclick2 = onclick2;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.park1_rec, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tv1.setText("车位号：" + list.get(position).getParkingSpaceName());
        holder.tv2.setText(bean.getKm());
        holder.tv3.setText(bean.getMoney());
        holder.tv4.setText(bean.getNumber());

        holder.btn1.setOnClickListener(v -> {
            onclick1.onclick(position);
        });

        holder.btn2.setOnClickListener(v -> {
            onclick2.onclick(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private Button btn1;
        private Button btn2;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.park1_rec_tv1);
            tv2 = itemView.findViewById(R.id.park1_rec_tv2);
            tv3 = itemView.findViewById(R.id.park1_rec_tv3);
            tv4 = itemView.findViewById(R.id.park1_rec_tv4);
            btn1 = itemView.findViewById(R.id.park1_rec_btn1);
            btn2 = itemView.findViewById(R.id.park1_rec_btn2);
        }
    }
}
