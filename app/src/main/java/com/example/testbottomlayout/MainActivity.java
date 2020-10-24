package com.example.testbottomlayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testbottomlayout.base.BaseFragment;
import com.example.testbottomlayout.fragment.*;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
//    根据position定位Fragment
    private int position = 0;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//    上次切换的Fragment
    private Fragment mContent;
    private ArrayList<BaseFragment> mfragment;

    private RadioGroup mRg_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        初始化View和Fragment
        initView();
        initFragment();
//        实现RadioButton的监听
        setListener();
    }

    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.homepage:
                        position = 0;
                        break;
                    case R.id.message:
                        position = 1;
                        break;
                    case R.id.mine:
                        position = 2;
                        break;
                    default:
                        position = 0;
                        break;

                }
                //        根据位置得到Fragment
                BaseFragment fragment = getFragment(position);
                //        替换
                switchContent(mContent,fragment);
            }
        });
    }
//根据position创建Fragment
    private BaseFragment getFragment(int position) {
         return mfragment.get(position);
    }
    //    切换fragment
    void switchContent(Fragment from, Fragment to){

        fragmentTransaction = fragmentManager.beginTransaction();
        if(from!=to){
//            设置上次切换的Fragment
            mContent = to;
            if(!to.isAdded()){
//                没有被添加
                if (null != from){
                    fragmentTransaction.hide(from);
                }
                fragmentTransaction.add(R.id.fl_content,to);
                fragmentTransaction.commit();
            }else {
                fragmentTransaction.hide(from).show(to).commit();
            }
        }
    }
    private void initFragment() {
        mfragment = new ArrayList<>();

        mfragment.add(new HomepageFragment());
        mfragment.add(new MessageFragment());
        mfragment.add(new MineFragment());
        fragmentTransaction.add(R.id.fl_content,getFragment(0)).commit();
        mContent = getFragment(0);
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_main = findViewById(R.id.rg_main);
        mRg_main.check(R.id.homepage);
    }
}
