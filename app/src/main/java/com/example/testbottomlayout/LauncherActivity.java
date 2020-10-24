package com.example.testbottomlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },2000);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
//        关闭当前页面
        finish();

    }
}
