package com.example.testbottomlayout.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.testbottomlayout.R;
import com.example.testbottomlayout.Tool_class.CheckUser;
import com.example.testbottomlayout.Tool_class.SendIM;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: Aori
 * @date: 2020/11/2
 * @describe:
 */
@SuppressLint("Registered")
public class RegisteredActivity extends Activity {
    private Button button_Registered;
    private EditText editText_User;
    private EditText editText_Password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        init_view();
    }
    @SuppressLint("LongLogTag")
    public void init_view(){
        SharedPreferences UserSp = getSharedPreferences("UserSp", Context.MODE_PRIVATE);
        button_Registered = findViewById(R.id.Button_Registered);
        editText_User = findViewById(R.id.editText_registered_User);
        editText_Password = findViewById(R.id.editText_Registered_password);
        button_Registered.setOnClickListener(v -> {
            String user = editText_User.getText().toString();
            String password = editText_Password.getText().toString();
//            获取用户名密码
            System.out.println(user+":"+password);

            FutureTask futureTask_SendIM =new FutureTask(new SendIM(user));
            Thread thread_SendIM = new Thread(futureTask_SendIM);


//          发送IM服务端的验证 然后注册IM的用户密码
            thread_SendIM.start();
            try {
                Log.e("futureTask_SendIM", (String) futureTask_SendIM.get());
                String get_IM = (String) futureTask_SendIM.get();
                JSONObject json_IM = new JSONObject(get_IM);
                Log.e("futureTask_SendIM", String.valueOf(json_IM.get("code")));
                if (!"200".equals(String.valueOf(json_IM.get("code")))){
                    new AlertDialog.Builder(RegisteredActivity.this)
                            .setTitle("注册失败")
                            .setMessage("返回状态码非200")
                            .setPositiveButton("确定",null)
                            .show();
                }else{
//                    Log.e("futureTask_SendIM", String.valueOf(json_IM.get("info")));
//                    JSONObject json_IMinfo = new JSONObject( json_IM.get("info").toString()) ;
//                    Log.e("futureTask_SendIM", String.valueOf(json_IMinfo.get("accid")));
//                    Log.e("futureTask_SendIM", String.valueOf(json_IMinfo.get("token")));
                    JSONObject json_IMinfo = new JSONObject(json_IM.get("info").toString()) ;

//                    将accid和token存入SharedPreferences
                    SharedPreferences.Editor editor = UserSp.edit();
                    editor.putString("accid", (String) json_IMinfo.get("accid"));
                    editor.putString("token", (String) json_IMinfo.get("token"));
                    editor.apply();

                }
            } catch ( JSONException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            FutureTask futureTask_CheckUser =new FutureTask(new CheckUser(user,password, UserSp.getString("accid",""),
                    UserSp.getString("token",""),"registered"));
            Thread CheckUser = new Thread(futureTask_CheckUser);

//          判断Django服务器的账号密码
//          根据服务器返回值来判断是否注册成功
            CheckUser.start();
            try {
                if (futureTask_CheckUser.get().equals("registered1")){
                    new AlertDialog.Builder(RegisteredActivity.this)
                            .setTitle("注册成功")
                            .setMessage("点击确定跳转至登录界面")
                            .setPositiveButton("确定", (dialog, which) -> {
                                Intent intent = new Intent(RegisteredActivity.this, LoginActivity.class);
                                startActivity(intent);
                            })
                            .show();
                if (futureTask_CheckUser.get().equals("registered0")){
                        new AlertDialog.Builder(RegisteredActivity.this)
                                .setTitle("注册失败")
                                .setMessage("点击确定跳转至注册界面")
                                .setPositiveButton("确定",null)
                                .show();
                    }
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }





        });
    }
}
