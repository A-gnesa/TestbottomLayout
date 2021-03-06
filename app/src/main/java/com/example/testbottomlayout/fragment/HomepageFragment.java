package com.example.testbottomlayout.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbottomlayout.Activity.P2PActivity;
import com.example.testbottomlayout.Activity.SCLActivity;
import com.example.testbottomlayout.R;
import com.example.testbottomlayout.recycleView.Homepage_RecyclerViewAdapter;
import com.example.testbottomlayout.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Aori
 * @date: 2020/10/24
 * @describe:主页Fragment
 */
public class HomepageFragment extends BaseFragment {
    //得到类的简写名称
    private static final String TAG = HomepageFragment.class.getSimpleName();
    List<Integer> list;
    RecyclerView recyclerView;
    Button firstfun_button;
    @Override
    protected View initView() {
        Log.e(TAG,"Fragment界面被初始化了");
        View view = View.inflate(mcontext, R.layout.fragment_recycler,null);
        recyclerView = view.findViewById(R.id.Recycler);
        list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(i);
        }
        //设置线性布局
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayout);
        //设置适配器
        Homepage_RecyclerViewAdapter recyclerViewAdapter = new Homepage_RecyclerViewAdapter(getActivity(), list);
        recyclerView.setAdapter(recyclerViewAdapter);

        firstfun_button = view.findViewById(R.id.first_fun);
        firstfun_button.setOnClickListener(v -> {
            Log.e("button","被点击了");
            Intent intent = new Intent(getActivity(), SCLActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"Fragment数据被初始化了");
    }
}
