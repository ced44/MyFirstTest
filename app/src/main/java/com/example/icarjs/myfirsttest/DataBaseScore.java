package com.example.icarjs.myfirsttest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by imie on 19/08/14.
 */
public class DataBaseScore extends SQLiteOpenHelper {

    private static final String TAG = "ScoreDAO";

    public static final String SCORE_KEY = "_id";
    public static final String NOM_USER = "nom";
    public static final String SCORE_USER = "score";

    public static final String SCORE_TABLE_NAME = "Score";

    public static final String SCORE_TABLE_CREATE =
            "CREATE TABLE " + SCORE_TABLE_NAME +
                "(" +
                    SCORE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOM_USER + " TEXT NOT NULL, " +
                    SCORE_USER + " INTEGER NOT NULL" +
                 ");";


    public DataBaseScore(Context context /*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context,"database_score.db",null,1);   // name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCORE_TABLE_CREATE);
        db.execSQL("INSERT INTO " + SCORE_TABLE_NAME + " VALUES(1,'score 1',100)" );
        db.execSQL("INSERT INTO " + SCORE_TABLE_NAME + " VALUES(2,'score 2',200)" );
        Log.w(TAG, SCORE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + SCORE_TABLE_NAME);
        onCreate(db);
    }
}
