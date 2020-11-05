package com.example.testbottomlayout.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.testbottomlayout.R;

import static com.netease.nimlib.sdk.NIMClient.initSDK;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        /**
         * @description 延迟当前启动页的进程（为了显示启动页） 利用Handler().postDelayed（）启动线程并且实现延迟。
         * @param void
         * @return void
         * @author Aori
         * @time 2020/10/24 15:49
         */
        new Handler().postDelayed(() -> startMainActivity(),1000);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
//        关闭当前页面
        finish();

    }
}
