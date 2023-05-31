package com.xiong.stockdiagnosticdiary.bean;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "hairstylist")
public class Hairstylist implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private String sex;
    private String phone;
    private String id_card;
    private Long birthday;
    private HairstylistGrade hairstylist_garde;
    @Embedded
    private Shop shop;

    private int shopId;
    private int gradeId;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "Hairstylist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", id_card='" + id_card + '\'' +
                ", birthday=" + birthday +
                ", hairstylist_garde=" + hairstylist_garde +
                ", shop=" + shop +
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public HairstylistGrade getHairstylist_garde() {
        return hairstylist_garde;
    }

    public void setHairstylist_garde(HairstylistGrade hairstylist_garde) {
        this.hairstylist_garde = hairstylist_garde;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }


}
