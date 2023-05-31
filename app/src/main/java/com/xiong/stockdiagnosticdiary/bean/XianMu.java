package com.xiong.stockdiagnosticdiary.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "item")
public class XianMu implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private Integer price;
    private Integer member_price;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", member_price=" + member_price +
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMember_price() {
        return member_price;
    }

    public void setMember_price(Integer member_price) {
        this.member_price = member_price;
    }
}
