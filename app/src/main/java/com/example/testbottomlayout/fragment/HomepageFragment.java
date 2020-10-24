package com.example.testbottomlayout.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testbottomlayout.base.BaseFragment;

/**
 * @author: Aori
 * @date: 2020/10/24
 * @describe:主页Fragment
 */
public class HomepageFragment extends BaseFragment {
    //得到类的简写名称
    private static final String TAG = HomepageFragment.class.getSimpleName();
    private TextView textView;

    @Override
    protected View initView() {
        Log.e(TAG,"Fragment界面被初始化了");
        textView = new TextView(mcontext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("主页");
        Log.e(TAG,"Fragment数据被初始化了");
    }
}
