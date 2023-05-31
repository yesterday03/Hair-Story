package com.xiong.stockdiagnosticdiary.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.xiong.stockdiagnosticdiary.bean.Hairstylist;
import com.xiong.stockdiagnosticdiary.bean.XianMu;

import java.util.List;
@Dao
public interface HairstylistDao {
    @Query("select * from hairstylist")
    List<Hairstylist> selectHairstylistAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHairstylistAll(List<Hairstylist> item);


    @Query("SELECT DISTINCT shop_name AS shop_name FROM hairstylist")
    List<String> getAllShopNames();
    @Query("SELECT * FROM hairstylist where shop_name=:shopName")
    List<Hairstylist> findShopName(String shopName);

    @Query("SELECT * FROM hairstylist where name=:name")
    Hairstylist findYuanGonName(String name);

    @Query("DELETE FROM hairstylist")
    void deleteAll();

}
