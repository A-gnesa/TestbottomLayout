package com.example.testbottomlayout;

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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
        Connect();
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
        public void Connect(){
//            String url = "http://127.0.0.1:8000/demo2";
            String url = "https://www.baidu.com/";
            OkHttpClient okHttpClient = new OkHttpClient();
            MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
            String requestBody = "I am Jdqm.";
            RequestBody body = RequestBody.create(mediaType, requestBody);
            final Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            final Call call = okHttpClient.newCall(request);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response response = call.execute();
                        String TAG="OKHTTP";
                        Log.d(TAG, "run: " + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
}
