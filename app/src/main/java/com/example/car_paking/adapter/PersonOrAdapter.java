package com.example.car_paking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_paking.R;
import com.example.car_paking.bean.ParkRecordBean2;
import com.example.car_paking.bean.PersonOrderBean;
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
public class PersonOrAdapter extends RecyclerView.Adapter<PersonOrAdapter.MyHolder> {

    private List<PersonOrderBean.DataBean> list = new ArrayList<>();
    private List<ParkRecordBean2> list2 = new ArrayList<>();
    private Context context;
    private MyOnclick onclick1;
    private MyOnclick onclick2;
    private SimpleDateFormat simpleDateFormat;
    private Date parse;
    private Date parse1;

    public PersonOrAdapter(List<PersonOrderBean.DataBean> list, List<ParkRecordBean2> list2, Context context, MyOnclick onclick1, MyOnclick onclick2) {
        this.list = list;
        this.list2 = list2;
        this.context = context;
        this.onclick1 = onclick1;
        this.onclick2 = onclick2;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_rec, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.tv1.setText("车牌号：" + list.get(position).getPlateNum());

        String time = list.get(position).getCreateTime().replace("T", " ");
        String time1 = list.get(position).getEndTime().replace("T", " ");
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parse = simpleDateFormat.parse(time);
            parse1 = simpleDateFormat.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (list.get(position).getType() == 1) {
            holder.tv2.setText("预约中");
            holder.btn1.setVisibility(View.VISIBLE);
            holder.btn2.setVisibility(View.VISIBLE);
            holder.tv4.setText("未入库");
        } else if (list.get(position).getType() == 2) {
            holder.tv2.setText("已入库");
            holder.btn1.setVisibility(View.INVISIBLE);
            holder.btn2.setVisibility(View.INVISIBLE);
            holder.tv4.setText(simpleDateFormat.format(parse1));
        }

        holder.tv3.setText(simpleDateFormat.format(parse));
        holder.tv5.setText(list2.get(position).getData().getParkName());

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
        private TextView tv5;
        private Button btn1;
        private Button btn2;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.order_rec_tv1);
            tv2 = itemView.findViewById(R.id.order_rec_tv2);
            tv3 = itemView.findViewById(R.id.order_rec_tv3);
            tv4 = itemView.findViewById(R.id.order_rec_tv4);
            tv5 = itemView.findViewById(R.id.order_rec_tv5);

            btn1 = itemView.findViewById(R.id.order_rec_btn1);
            btn2 = itemView.findViewById(R.id.order_rec_btn2);
        }
    }
}
