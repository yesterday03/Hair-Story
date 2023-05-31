package com.xiong.stockdiagnosticdiary.bean;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
    private Integer id;
    private String phone;
    private String name;
    private JsonObject birthday;
    private Integer balance;
    private Integer total_amount;
    private JsonObject register_date;
    private MemberGrade member_grade;
    private String info;


//    @Override
//    public String toString() {
//        return "Member{" +
//                "id=" + id +
//                ", phone='" + phone + '\'' +
//                ", name='" + name + '\'' +
//                ", birthday=" + birthday +
//                ", balance=" + balance +
//                ", total_amount=" + total_amount +
//                ", register_date=" + register_date +
//                ", member_grade=" + member_grade +
//                ", info='" + info + '\'' +
//                '}';
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject getBirthday() {
        return birthday;
    }

    public void setBirthday(JsonObject birthday) {
        this.birthday = birthday;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

    public JsonObject getRegister_date() {
        return register_date;
    }

    public void setRegister_date(JsonObject register_date) {
        this.register_date = register_date;
    }

    public MemberGrade getMember_grade() {
        return member_grade;
    }

    public void setMember_grade(MemberGrade member_grade) {
        this.member_grade = member_grade;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }




}
