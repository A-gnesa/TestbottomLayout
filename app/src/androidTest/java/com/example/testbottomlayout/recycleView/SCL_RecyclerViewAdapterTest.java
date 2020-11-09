package com.example.testbottomlayout.recycleView;

import android.widget.Toast;

import com.example.testbottomlayout.Page.SCL_question;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * @author: Aori
 * @date: 2020/11/8
 * @describe:
 */
public class SCL_RecyclerViewAdapterTest {
    public static void main(String[] args) {
        String url = "http://10.206.150.208:8000/demo2/SCL90/";
        String result;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .header("Connection", "close")
                .build();
        final Call call = okHttpClient.newCall(request);
       call.enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
           }
           @Override
           public void onResponse(Call call, Response response) throws IOException {
               Document doc = Jsoup.parse(response.body().string());
               Elements elements = doc.select("div");
               int i = 1;
               for (Element ele : elements) {
                   String[] str =  ele.text().split(",");
                   System.out.println(str[0]+"________________"+str[1]);
               }
           }
       });
    }
}