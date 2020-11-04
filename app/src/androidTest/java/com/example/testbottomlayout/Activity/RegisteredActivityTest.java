package com.example.testbottomlayout.Activity;

import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: Aori
 * @date: 2020/11/4
 * @describe:
 */
public class RegisteredActivityTest {

    @Test
    public void init_view() {
        String json = "{\"code\":200,\"info\":{\"name\":\"\",\"accid\":\"test2\",\"token\":\"0904ba0feb5708012e635663b4a934cc\"}}";
        try {
            JSONObject json_IM = new JSONObject(json);
            if (!"200".equals(String.valueOf(json_IM.get("code")))){
                System.out.println((json_IM.get("code")));
            }
            System.out.println((json_IM.get("info")));
                Log.e("200:",String.valueOf(json_IM.get("code")));
//                JSONObject json_IMinfo = new JSONObject((String) json_IM.get("info")) ;
//                Log.e("accid",(String) json_IMinfo.get("accid"));
//                Log.e("token",(String) json_IMinfo.get("token"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}