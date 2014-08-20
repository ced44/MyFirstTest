package com.example.icarjs.myfirsttest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by imie on 19/08/14.
 */
public class DataBaseScore extends SQLiteOpenHelper {
    public static final String SCORE_KEY = "id";
    public static final String NOM_USER = "nom";
    public static final String SCORE_USER = "score";

    public static final String SCORE_TABLE_NAME = "Score";
    public static final String SCORE_TABLE_CREATE =
            "CREATE TABLE " + SCORE_TABLE_NAME + " (" + SCORE_KEY +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOM_USER +
            " TEXT, " + SCORE_USER + " TEXT);";

    public DataBaseScore(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("DataBaseScore","constructeur");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DataBaseScore","onCreate()");

        db.execSQL(SCORE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
