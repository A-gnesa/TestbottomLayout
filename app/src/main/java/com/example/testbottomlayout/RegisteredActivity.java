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

    }
    public void init_view(){
        button_Registered = findViewById(R.id.button_register);
        editText_User = findViewById(R.id.editText_registered_password);
        editText_Password = findViewById(R.id.editText_repeat_password);
        button_Registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editText_User.getText().toString();
                String password = editText_Password.getText().toString();
                System.out.println(user+":"+password);
                FutureTask futureTask =new FutureTask(new CheckUser(user,password,"registered"));
                new Thread(futureTask).start();
                try {
                    if (futureTask.get().equals("registered1")){
                            new AlertDialog.Builder(RegisteredActivity.this)
                                    .setTitle("注册成功")
                                    .setMessage("点击确定跳转至登录界面")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(RegisteredActivity.this,LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
