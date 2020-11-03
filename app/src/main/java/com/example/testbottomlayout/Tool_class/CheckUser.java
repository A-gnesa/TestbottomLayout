package com.example.testbottomlayout.Tool_class;

import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author: Aori
 * @date: 2020/11/1
 * @describe: 登录或者注册
 */
public class CheckUser implements Callable {
    private String username;
    private String password;
    private String operation;

    public CheckUser(String username, String password, String operation) {
        this.username = username;
        this.password = password;
        this.operation = operation;
    }

    @Override
    public Object call() throws Exception {
        String url;
//        校园网
        url = "http://10.206.150.208:8000/demo2/"+operation+"/";
//        手机热点
//        url = " http://172.20.10.2:8000/demo2/"+operation+"/";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        JSONObject json;
        json = new JSONObject();
        json.put("User", username);
        json.put("password", password);
        RequestBody body = RequestBody.create(mediaType, String.valueOf(json));
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final Call call = okHttpClient.newCall(request);
        Response response = call.execute();         //必须子线程执行
        assert response.body() != null;
        String result = response.body().string();
        return result;
    }
}
