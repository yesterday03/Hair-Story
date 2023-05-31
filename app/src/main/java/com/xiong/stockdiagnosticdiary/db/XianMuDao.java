package com.xiong.stockdiagnosticdiary.db;

import android.content.ClipData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.xiong.stockdiagnosticdiary.bean.XianMu;

import java.util.List;
@Dao
public interface XianMuDao {
    @Query("select id,title,price,member_price from item")
    List<XianMu> selectItemAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItemAll(List<XianMu> item);

    @Query("DELETE FROM item")
    void deleteAll();
}
