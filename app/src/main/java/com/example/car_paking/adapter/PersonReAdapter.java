package com.example.car_paking.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_paking.R;
import com.example.car_paking.bean.Park1Bean;
import com.example.car_paking.bean.ParkRecordBean2;
import com.example.car_paking.bean.ParkingBean2;
import com.example.car_paking.bean.RecordBean;
import com.example.car_paking.inter.MyOnclick;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 停车场查询适配器
 */
public class PersonReAdapter extends RecyclerView.Adapter<PersonReAdapter.MyHolder> {

    private List<RecordBean.DataBean> list = new ArrayList<>();
    private List<ParkRecordBean2> list2 = new ArrayList<>();
    private Context context;
    private MyOnclick onclick1;
    private SimpleDateFormat simpleDateFormat;
    private Date parse;


    public PersonReAdapter(List<RecordBean.DataBean> list, List<ParkRecordBean2> list2, Context context, MyOnclick onclick1) {
        this.list = list;
        this.list2 = list2;
        this.context = context;
        this.onclick1 = onclick1;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.record_rec, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.tv1.setText("车牌号：" + list.get(position).getPlateNum());

        String time = list.get(position).getCreateTime().replace("T", " ");
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

        if (list.get(position).getType() == 1) {
            if (in < 1){
                holder.tv2.setText("正在计费中：" + list2.get(position).getData().getPrice());
            }else {
                holder.tv2.setText("正在计费中：" + in * list2.get(position).getData().getPrice());
            }
            holder.tv4.setText("正在计时中");
            holder.btn1.setVisibility(View.VISIBLE);
        } else if (list.get(position).getType() == 2) {
            holder.tv2.setText("收费金额：" + list.get(position).getMoney());
            holder.tv4.setText(list.get(position).getEndTime());
            holder.btn1.setVisibility(View.INVISIBLE);
        }

        holder.tv3.setText(simpleDateFormat.format(parse));
        holder.tv5.setText(list2.get(position).getData().getParkName());

        holder.btn1.setOnClickListener(v -> {
            onclick1.onclick(position);
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
        private TextView tv5;
        private Button btn1;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.record_rec_tv1);
            tv2 = itemView.findViewById(R.id.record_rec_tv2);
            tv3 = itemView.findViewById(R.id.record_rec_tv3);
            tv4 = itemView.findViewById(R.id.record_rec_tv4);
            tv5 = itemView.findViewById(R.id.record_rec_tv5);

            btn1 = itemView.findViewById(R.id.record_rec_btn);
        }
    }
}
