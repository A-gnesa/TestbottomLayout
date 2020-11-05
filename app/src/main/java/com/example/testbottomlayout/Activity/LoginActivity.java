package com.example.testbottomlayout.Activity;

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
import com.example.testbottomlayout.User;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
    private User user;

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
                FutureTask futureTask_CheckUser =new FutureTask(new CheckUser(user,password,"login"));
                Thread thread_CheckUser = new Thread(futureTask_CheckUser);
                thread_CheckUser.start();
                assert UserSp != null;
                LoginInfo loginInfo = new LoginInfo(UserSp.getString("accid",""),
                        UserSp.getString("token",""));
                RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
//                  登陆成功会调用此接口
                    }

                    @Override
                    public void onFailed(int code) {
                        String str_code = String.valueOf(code);
                        Log.e("LoginFailed",str_code);
                    }

                    @Override
                    public void onException(Throwable exception) {
//                  登录报错会调用此接口
                    }
                };
//                登录IM↓
                NIMClient.getService(AuthService.class).login(loginInfo).setCallback(callback);
//              根据服务器的返回参数判断登录是否成功
                if (futureTask_CheckUser.get().equals("login1")){
                        SharedPreferences.Editor editor = UserSp.edit();
                        editor.putString("user",user);
                        editor.putString("password",password);
                        editor.putBoolean("login",true);
                        editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }if (futureTask_CheckUser.get().equals("login0")){
                    new  AlertDialog.Builder(LoginActivity.this)
                            .setTitle("登录失败！" )
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setMessage("账号密码错误")
                            .setPositiveButton("注册账号", (dialog, which) -> {
                                Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                                startActivity(intent);
                            })
                            .setNegativeButton("取消" ,  null )
                            .setNeutralButton("忘记密码",null)
                            .show();
                }

            } catch (InterruptedException | ExecutionException e) {
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
