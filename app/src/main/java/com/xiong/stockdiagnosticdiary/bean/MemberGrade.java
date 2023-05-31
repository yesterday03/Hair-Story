package com.xiong.stockdiagnosticdiary.bean;

import java.io.Serializable;

public class MemberGrade implements Serializable {
    private Integer id;
    private String title;
    private Integer initial_amount;

    @Override
    public String toString() {
        return "MemberGrade{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", initial_amount=" + initial_amount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getInitial_amount() {
        return initial_amount;
    }

    public void setInitial_amount(Integer initial_amount) {
        this.initial_amount = initial_amount;
    }

}
