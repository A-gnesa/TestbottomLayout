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

import com.example.testbottomlayout.R;
import com.example.testbottomlayout.base.BaseFragment;

import java.util.zip.Inflater;

/**
 * @author: Aori
 * @date: 2020/10/24
 * @describe:主页Fragment
 */
public class HomepageFragment extends BaseFragment {
    //得到类的简写名称
    private static final String TAG = HomepageFragment.class.getSimpleName();

    @Override
    protected View initView() {
        Log.e(TAG,"Fragment界面被初始化了");
        View view = View.inflate(mcontext, R.layout.fragment_recycler,null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"Fragment数据被初始化了");
    }
}
