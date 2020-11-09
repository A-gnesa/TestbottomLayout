package com.example.testbottomlayout.Page;

/**
 * @author: Aori
 * @date: 2020/11/7
 * @describe:
 */
public class page_SCLIterator  {
    private  page_SCL scl;
    page_SCLIterator(page_SCL scl) {
        this.scl = scl;
    }

    public void add(SCL_question question,SCL_answer answer){
        scl.getList().put(question,answer);
    }

}
