package com.xiong.stockdiagnosticdiary.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.xiong.stockdiagnosticdiary.bean.Hairstylist;
import com.xiong.stockdiagnosticdiary.bean.HairstylistGrade;
import com.xiong.stockdiagnosticdiary.bean.XianMu;

@Database(entities = {XianMu.class, Hairstylist.class},version = 1,exportSchema = false)
@TypeConverters({HairstylistGradeTypeConverter.class, ShopTypeConverter.class})
public abstract class LearningDatabase extends RoomDatabase {

    private  static  LearningDatabase databaseInstance;

    public static synchronized LearningDatabase getLearningDatabase(Context context){
        if(databaseInstance == null){
            String DATABASE_NAME = "mrmf.db";//context.getDatabasePath("xuexila_data.").getAbsolutePath();
            databaseInstance = Room.databaseBuilder(context,LearningDatabase.class,DATABASE_NAME).
                    allowMainThreadQueries().
                    build();
        }
        return databaseInstance;
    }

    public abstract HairstylistDao hairstylistDao();

    public abstract  XianMuDao xianMuDao();

}
