package com.xiong.stockdiagnosticdiary.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiong.stockdiagnosticdiary.bean.HairstylistGrade;
import com.xiong.stockdiagnosticdiary.bean.Shop;

import java.lang.reflect.Type;

public class ShopTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static Shop fromString(String value) {
        Type type = new TypeToken<Shop>(){}.getType();
        return gson.fromJson(value, type);
    }

    @TypeConverter
    public static String fromHairstylistGrade(Shop grade) {
        return gson.toJson(grade);
    }
}

