package com.example.testbottomlayout.recycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbottomlayout.R;

import java.util.List;

/**
 * @author: Aori
 * @date: 2020/10/24
 * @describe:RecycleView的适配器
 */
public class Homepage_RecyclerViewAdapter extends RecyclerView.Adapter<Homepage_RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Integer> list;

    //构造方法
    public Homepage_RecyclerViewAdapter(Context context, List<Integer> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepassage_recycleview_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int i = list.get(position);
        holder.textView.setText("这是第"+i+"个测试");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //内部类 绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_item);

        }
    }
}