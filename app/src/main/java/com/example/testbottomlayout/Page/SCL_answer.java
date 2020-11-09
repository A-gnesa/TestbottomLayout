package com.example.testbottomlayout.Page;

/**
 * @author: Aori
 * @date: 2020/11/7
 * @describe:
 */
public class SCL_answer {
    private int score;

    public SCL_answer(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }


    public String getAnswerString(){
        switch (this.score){
            case '1':
                return "从无";
            case '2':
                return "很轻";
            case '3':
                return "中等";
            case '4':
                return "偏重";
            case '5':
                return "很重";

                default:
                    return "错误";
        }
    }
}
