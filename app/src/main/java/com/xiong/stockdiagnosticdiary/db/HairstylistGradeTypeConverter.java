package com.xiong.stockdiagnosticdiary.db;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiong.stockdiagnosticdiary.bean.HairstylistGrade;

import java.lang.reflect.Type;

public class HairstylistGradeTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static HairstylistGrade fromString(String value) {
        Type type = new TypeToken<HairstylistGrade>(){}.getType();
        return gson.fromJson(value, type);
    }

    @TypeConverter
    public static String fromHairstylistGrade(HairstylistGrade grade) {
        return gson.toJson(grade);
    }
}

