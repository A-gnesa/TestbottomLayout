package com.example.testbottomlayout.Page;

import java.util.HashMap;

/**
 * @author: Aori
 * @date: 2020/11/9
 * @describe:
 */
public class SCL_score {
    private int allscore = 0;
    private HashMap<String,Integer> scoreMap = new HashMap<>();
    public void putScore(SCL_question question,SCL_answer answer){
        scoreMap.put(question.getDivisor(),answer.getScore());
        allscore = allscore+answer.getScore();
    }
    public int getScore(String key){
       return scoreMap.get(key);
    }
    public int getAllscore(){
        return this.allscore;
    }
}
