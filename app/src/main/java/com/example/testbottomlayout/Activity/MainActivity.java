package com.example.testbottomlayout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testbottomlayout.R;
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



    private Button firstfun_button;
    private RadioGroup mRg_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        初始化View和Fragment
        initView();
        initFragment();
//        实现RadioButton的监听
        setRadioButtonListener();
    }


    private void setRadioButtonListener() {
        mRg_main.setOnCheckedChangeListener((group, checkedId) -> {
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
//    public void Connect(){
//        String host = "10.206.150.208";
//        int port = 55533;
//        // 与服务端建立连接
//        try {
//            Socket socket = new Socket(host, port);
//            Log.e("socket",socket.toString());
//            OutputStream outputStream = socket.getOutputStream();
//            String message="你好  yiwangzhibujian";
//            socket.getOutputStream().write(message.getBytes("UTF-8"));
//            socket.shutdownOutput();
//            InputStream inputStream = socket.getInputStream();
//            byte[] bytes = new byte[1024];
//            int len;
//            StringBuilder sb = new StringBuilder();
//            while ((len = inputStream.read(bytes)) != -1) {
//                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//                sb.append(new String(bytes, 0, len,"UTF-8"));
//            }
//            inputStream.close();
//            outputStream.close();
//            socket.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onBackPressed() {
        /**
         * @description 设置不可返回为登录界面
         * @param void
         * @return void
         * @author Aori
         * @time 2020/11/1 22:44
         */
    }
}
