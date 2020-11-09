package com.example.testbottomlayout.recycleView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbottomlayout.Page.SCL_answer;
import com.example.testbottomlayout.Page.SCL_question;
import com.example.testbottomlayout.Page.page_SCL;
import com.example.testbottomlayout.Page.page_SCLIterator;
import com.example.testbottomlayout.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: Aori
 * @date: 2020/11/7
 * @describe:
 */
public class SCL_RecyclerViewAdapter extends RecyclerView.Adapter<SCL_RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private static ArrayList<SCL_question> list = new ArrayList<>();
    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();

    public ArrayList<RadioGroup> getRadioGroups() {
        return radioGroups;
    }

    public SCL_RecyclerViewAdapter(Context context) {

        this.context = context;
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
                    list.add(new SCL_question(str[0],i,str[1]));
                }
            }
        });
    }

    @NonNull
    @Override
//    设置界面
    public SCL_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scl_recycler_item,parent,false);
        SCL_RecyclerViewAdapter.MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
//  构建界面
    @Override
    public void onBindViewHolder(@NonNull SCL_RecyclerViewAdapter.MyViewHolder holder, int position) {
//        设置当前问题文本
        SCL_question question = list.get(position);
        holder.question.setText(question.getQuestion());
        radioGroups.add(holder.SCLRadioGroup);
//        page_SCLIterator iterator = page_scl.createItertor();
//        switch (holder.SCLRadioGroup.getCheckedRadioButtonId()){
//            case R.id.SCL_RadioButton1:
//                iterator.add(question,new SCL_answer(1));
//                break;
//            case R.id.SCL_RadioButton2:
//                iterator.add(question,new SCL_answer(2));
//                break;
//            case R.id.SCL_RadioButton3:
//                iterator.add(question,new SCL_answer(3));
//                break;
//            case R.id.SCL_RadioButton4:
//                iterator.add(question,new SCL_answer(4));
//                break;
//            case R.id.SCL_RadioButton5:
//                iterator.add(question,new SCL_answer(5));
//                break;
//            default:
//                Log.e("onBindViewHolder", "获取答案失败，问题 "+position);
//                iterator.add(question,new SCL_answer(0));
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<SCL_question> getList() {
        return list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioGroup  SCLRadioGroup;

        public MyViewHolder(@NonNull View itemView) {
//            绑定控件
            super(itemView);
            question = itemView.findViewById(R.id.question_TextView);
            SCLRadioGroup = itemView.findViewById(R.id.SCL_RadioGroup);

        }
    }
}
