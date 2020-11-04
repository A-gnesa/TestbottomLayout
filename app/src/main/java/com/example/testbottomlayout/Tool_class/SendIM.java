package com.example.testbottomlayout.Tool_class;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author: Aori
 * @date: 2020/11/3
 * @describe:
 */
public class SendIM implements Callable {
    @Override
    public Object call() throws Exception {
        String appKey = "01f8d0476d3c8f94e95a87ab2063e89d";
        String appSecret = "9d10f78694d8";
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码





        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.netease.im/nimserver/user/create.action";
//        String url = "http://10.206.150.208:8000/demo2/test/";
        FormBody formBody = new FormBody.Builder().add("accid", "helloworld").build();
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("AppKey", appKey)
                .addHeader("CurTime", curTime)
                .addHeader("nonce",nonce)
                .addHeader("CheckSum", checkSum)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();

        final Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String result = response.body().string();
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
