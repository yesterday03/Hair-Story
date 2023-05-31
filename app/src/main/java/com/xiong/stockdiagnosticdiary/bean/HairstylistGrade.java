package com.xiong.stockdiagnosticdiary.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
public class HairstylistGrade implements Serializable {
    private Integer id;
    private String title;
    private Integer basic_salary;
    private Double royalty_rate;

    public HairstylistGrade(Integer id, String title, Integer basic_salary, Double royalty_rate) {
        this.id = id;
        this.title = title;
        this.basic_salary = basic_salary;
        this.royalty_rate = royalty_rate;
    }

    @Override
    public String toString() {
        return "HairstylistGrade{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", basic_salary=" + basic_salary +
                ", royalty_rate=" + royalty_rate +
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

    public Integer getBasic_salary() {
        return basic_salary;
    }

    public void setBasic_salary(Integer basic_salary) {
        this.basic_salary = basic_salary;
    }

    public Double getRoyalty_rate() {
        return royalty_rate;
    }

    public void setRoyalty_rate(Double royalty_rate) {
        this.royalty_rate = royalty_rate;
    }
}
