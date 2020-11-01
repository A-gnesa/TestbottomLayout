package com.example.testbottomlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

/**
 * @author: Aori
 * @date: 2020/11/1
 * @describe:登录页面
 */
class LoginActivity extends Activity {
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
    }
    public void init_view(){
        editText_User = findViewById(R.id.editText_User);
        editText_Password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);
        button_register=findViewById(R.id.button_register);

    }
    
    public void verification_login(){
        /**
         * @description 验证登录
         * @param void
         * @return void
         * @author Aori
         * @time 2020/11/1 21:19
         */
        String user = editText_User.getText().toString();
        String password = editText_Password.getText().toString();
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.equals(password)){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
