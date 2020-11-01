package com.example.testbottomlayout.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import java.net.InetAddress;

import com.example.testbottomlayout.base.BaseFragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author: Aori
 * @date: 2020/10/24
 * @describe:
 */
public class MineFragment extends BaseFragment {
    //得到类的简写名称
    private static final String TAG = HomepageFragment.class.getSimpleName();
    private TextView textView;
    Socket socket = null;
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
        textView.setText("我的");
        Log.e(TAG,"Fragment数据被初始化了");
    }
}
