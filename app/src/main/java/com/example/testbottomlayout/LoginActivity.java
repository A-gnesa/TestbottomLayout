package com.example.testbottomlayout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.testbottomlayout.Tool_class.CheckUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author: Aori
 * @date: 2020/11/1
 * @describe:登录页面
 */
public class LoginActivity extends Activity {
    private EditText editText_User;
    private EditText editText_Password;
    private Button button_login;
    private Button button_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init_view();
        verification_login();
        Registered_event();
    }

    public void init_view() {
        editText_User = findViewById(R.id.editText_User);
        editText_Password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);
        button_register = findViewById(R.id.button_register);

    }

    public void verification_login() {
        /**
         * @description 验证登录
         * @param void
         * @return void
         * @author Aori
         * @time 2020/11/1 21:19
         */
        SharedPreferences UserSp = this.getSharedPreferences("UserSp", Context.MODE_PRIVATE);
        System.out.println(UserSp);
        if (UserSp!=null&&UserSp.getBoolean("login",false)){
            editText_User.setText(UserSp.getString("user","12"));
            editText_Password.setText(UserSp.getString("password","12"));
        }

        button_login.setOnClickListener(v -> {
            try {
                String user = editText_User.getText().toString();
                String password = editText_Password.getText().toString();


//                自己写的工具类用来获取服务器连接
                FutureTask futureTask =new FutureTask(new CheckUser(user,password,"login"));
                new Thread(futureTask).start();
//              根据服务器的返回参数判断登录是否成功
                if (futureTask.get().equals("login1")){
                        SharedPreferences.Editor editor = UserSp.edit();
                        editor.putString("user",user);
                        editor.putString("password",password);
                        editor.putBoolean("login",true);
                        editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }if (futureTask.get().equals("login0")){
                    new  AlertDialog.Builder(LoginActivity.this)
                            .setTitle("登录失败！" )
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setMessage("账号密码错误")
                            .setPositiveButton("注册账号", (dialog, which) -> {
                                Intent intent = new Intent(LoginActivity.this,RegisteredActivity.class);
                                startActivity(intent);
                            })
                            .setNegativeButton("取消" ,  null )
                            .setNeutralButton("忘记密码",null)
                            .show();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
    public void Registered_event(){
        button_register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,RegisteredActivity.class);
            startActivity(intent);
        });
    }
}
