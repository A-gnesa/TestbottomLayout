package com.example.testbottomlayout.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Aori
 * @date: 2020/11/7
 * @describe:
 */
public class page_SCL {
    private HashMap<SCL_question,SCL_answer> list;

    public page_SCL(HashMap<SCL_question, SCL_answer> list) {
        this.list = list;
    }

    public page_SCL() {
        list = null;
    }

    public Map<SCL_question, SCL_answer> getList() {
        return list;
    }

    public page_SCLIterator createItertor(){
        return new page_SCLIterator(this);
    }
}
