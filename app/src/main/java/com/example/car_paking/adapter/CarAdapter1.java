package com.example.car_paking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.car_paking.R;
import com.example.car_paking.bean.ParkingBean;
import com.example.car_paking.inter.MyOnclick;
import com.example.car_paking.net.Ok;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车场查询适配器
 */
public class CarAdapter1 extends RecyclerView.Adapter<CarAdapter1.MyHolder> {

    private List<ParkingBean.DataBean> list = new ArrayList<>();
    private Context context;
    private MyOnclick onclick;

    public CarAdapter1(List<ParkingBean.DataBean> list, Context context, MyOnclick onclick) {
        this.list = list;
        this.context = context;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.park_rec, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tv1.setText(list.get(position).getParkName());
        holder.tv2.setText(position + 10 + "公里");
        holder.tv3.setText("停车位" + list.get(position).getAllPark() + "个");
        holder.tv4.setText("停车费用" + list.get(position).getPrice() + "元/小时");
        Glide.with(context).load(Ok.URL + list.get(position).getImgUrl()).into(holder.img);
        holder.layout.setOnClickListener(v -> {
            onclick.onclick(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private RelativeLayout layout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.park_rec_img);
            tv1 = itemView.findViewById(R.id.park_rec_tv1);
            tv2 = itemView.findViewById(R.id.park_rec_tv2);
            tv3 = itemView.findViewById(R.id.park_rec_tv3);
            tv4 = itemView.findViewById(R.id.park_rec_tv4);
            layout = itemView.findViewById(R.id.park_rec_layout);

        }
    }
}
