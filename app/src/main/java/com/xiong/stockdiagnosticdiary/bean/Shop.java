package com.xiong.stockdiagnosticdiary.bean;

import androidx.room.ColumnInfo;

import java.io.Serializable;

public class Shop implements Serializable {
    @ColumnInfo(name = "shop_id")
    private Integer id;
    @ColumnInfo(name = "shop_name")
    private String name;
    private String address;
    private String shop_grade;
    private String login_account;
    private String login_password;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", shop_grade='" + shop_grade + '\'' +
                ", login_account='" + login_account + '\'' +
                ", login_password='" + login_password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShop_grade() {
        return shop_grade;
    }

    public void setShop_grade(String shop_grade) {
        this.shop_grade = shop_grade;
    }

    public String getLogin_account() {
        return login_account;
    }

    public void setLogin_account(String login_account) {
        this.login_account = login_account;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }
}
