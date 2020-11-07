package com.example.testbottomlayout.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.testbottomlayout.R;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.util.List;

/**
 * @author: Aori
 * @date: 2020/11/5
 * @describe:
 */
public class P2PActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences UserSp = getSharedPreferences("UserSp", Context.MODE_PRIVATE);
        String accid = UserSp.getString("accid","");
//        String accid = "";
        // 以单聊类型为例
        SessionTypeEnum sessionType = SessionTypeEnum.P2P;
        String text = "this is an example";
         // 创建一个文本消息
        IMMessage textMessage = MessageBuilder.createTextMessage(accid, sessionType, text);
        // 发送给对方
        NIMClient.getService(MsgService.class).sendMessage(textMessage, false).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void param) {
                Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(getApplicationContext(),"发送失败,失败代码为"+code,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onException(Throwable exception) {
                Toast.makeText(getApplicationContext(),"发送出错",Toast.LENGTH_SHORT).show();
            }
        });





    }
}
