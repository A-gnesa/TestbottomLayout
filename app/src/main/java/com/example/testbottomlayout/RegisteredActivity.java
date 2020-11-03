package com.example.testbottomlayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.testbottomlayout.Tool_class.CheckUser;

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
    public void init_view(){
        button_Registered = findViewById(R.id.Button_Registered);
        editText_User = findViewById(R.id.editText_registered_User);
        editText_Password = findViewById(R.id.editText_Registered_password);
        button_Registered.setOnClickListener(v -> {
            String user = editText_User.getText().toString();
            String password = editText_Password.getText().toString();
//            获取用户名密码
            System.out.println(user+":"+password);
            FutureTask futureTask =new FutureTask(new CheckUser(user,password,"registered"));
            new Thread(futureTask).start();
//            根据服务器返回值来判断是否注册成功
            try {
                if (futureTask.get().equals("registered1")){
                        new AlertDialog.Builder(RegisteredActivity.this)
                                .setTitle("注册成功")
                                .setMessage("点击确定跳转至登录界面")
                                .setPositiveButton("确定", (dialog, which) -> {
                                    Intent intent = new Intent(RegisteredActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                })
                                .show();
                } if (futureTask.get().equals("registered0")){
                    new AlertDialog.Builder(RegisteredActivity.this)
                            .setTitle("注册失败")
                            .setMessage("点击确定跳转至注册界面")
                            .setPositiveButton("确定",null)
                            .show();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }
}
