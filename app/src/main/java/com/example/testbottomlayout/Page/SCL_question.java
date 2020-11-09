package com.example.testbottomlayout.Page;

/**
 * @author: Aori
 * @date: 2020/11/7
 * @describe:
 */
public class SCL_question {
    private String question;
    private int id;
    private String divisor;

    public String getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }

    public String getDivisor() {
        return divisor;
    }

    public SCL_question(String question, int id, String divisor) {
        this.question = question;
        this.id = id;
        this.divisor = divisor;
    }
}
