package com.example.testbottomlayout.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author: Aori
 * @date: 2020/10/24
 * @describe:基类，公共类
 * HomepageFragment，MessageFragment，MineFragment等都要继承该类
 */
public abstract class BaseFragment extends Fragment {
//    上下文
    protected Context mcontext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }
    /**
     * @description 强制子类重写initView，实现子类特有的ui
     * @param 
     * @return View
     * @author Aori
     * @time 2020/10/24 18:24
     */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //  当子类需要初始化数据时，可以重写该方法
    protected  void initData(){}
}
