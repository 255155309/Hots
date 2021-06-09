package com.example.qimo.bean;

/**
 * Created by Administrator on 2021/4/21.
 */

public class Words {
    private String hot_word;
    private String hot_word_num;
    private String hot_word_url;

    public Words(String hot_word, String hot_word_num, String hot_word_url) {
        this.hot_word = hot_word;
        this.hot_word_num = hot_word_num;
        this.hot_word_url = hot_word_url;
    }

    public String getHot_word() {
        return hot_word;
    }

    public void setHot_word(String hot_word) {
        this.hot_word = hot_word;
    }

    public String getHot_word_num() {
        return hot_word_num;
    }

    public void setHot_word_num(String hot_word_num) {
        this.hot_word_num = hot_word_num;
    }

    public String getHot_word_url() {
        return hot_word_url;
    }

    public void setHot_word_url(String hot_word_url) {
        this.hot_word_url = hot_word_url;
    }
}
