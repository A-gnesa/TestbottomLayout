package com.example.testbottomlayout.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbottomlayout.Page.SCL_answer;
import com.example.testbottomlayout.Page.SCL_question;
import com.example.testbottomlayout.Page.SCL_score;
import com.example.testbottomlayout.Page.page_SCL;
import com.example.testbottomlayout.R;
import com.example.testbottomlayout.recycleView.Homepage_RecyclerViewAdapter;
import com.example.testbottomlayout.recycleView.SCL_RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: Aori
 * @date: 2020/11/8
 * @describe:
 */
public class SCLActivity extends Activity {
    private RecyclerView recyclerView;
    private Button SCL_button;
    private SCL_RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scl90);
        initView();
    }

    private void initView() {
        Context context = this;
        recyclerView = findViewById(R.id.SCL_recycler);
        //设置线性布局
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        new Thread(() -> {
            //设置适配器
            recyclerViewAdapter = new SCL_RecyclerViewAdapter(context);
            recyclerView.setAdapter(recyclerViewAdapter);
        }).start();
        SCL_button = findViewById(R.id.SCL_button);
        SCL_button.setOnClickListener(v -> {
            ArrayList<RadioGroup> radioGroups = recyclerViewAdapter.getRadioGroups();
//            i 为问题编号 j为答案编号
            ArrayList<SCL_question> questions = recyclerViewAdapter.getList();
            SCL_score score = new SCL_score();
            for (int i = 0; i < radioGroups.size();i++){
                for (int j = 0; j< radioGroups.get(i).getChildCount(); j++){
                    RadioButton radioButton = (RadioButton) radioGroups.get(i).getChildAt(j);
                    if (radioButton.isChecked()){
                        SCL_answer answer = new SCL_answer(j+1);
                        score.putScore(questions.get(i),answer);
                    }
                }
            }
            Log.e("score:", String.valueOf(score.getAllscore()));
        });
    }
}
