package com.example.testbottomlayout.Application;

import android.app.Application;
import android.graphics.Path;


import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;

import static com.netease.nimlib.sdk.NIMClient.initSDK;

/**
 * @author: Aori
 * @date: 2020/11/3
 * @describe:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NIMClient.init(this, loginInfo(), options());
    }
//    SDK设置
    private SDKOptions options() {
        SDKOptions options = new SDKOptions();
        return options;
    }
//  登录设置
    private LoginInfo loginInfo() {
        return null;
    }


}
