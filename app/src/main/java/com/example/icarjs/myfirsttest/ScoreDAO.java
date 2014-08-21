package com.example.icarjs.myfirsttest;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
 * Created by imie on 19/08/14.
 */
public class ScoreDAO extends DAOBase {

    public static final String TABLE_NAME = "Score";
    public static final String _ID = "_id";
    public static final String NOM = "nom";
    public static final String SCORE = "score";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOM + " TEXT NOT NULL, " + SCORE + " INTEGER NOT NULL);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    private static final String TAG = "ScoreDAO";

    public ScoreDAO(Context pContext) {
        super(pContext);
    }


    public long ajouterScore(String nom, Integer score) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(NOM, nom);
        initialValues.put(SCORE, score);

        return mDb.insert(TABLE_NAME, null, initialValues);
    }

    public boolean deleteAllScores() {

        int doneDelete = 0;
        doneDelete = mDb.delete(TABLE_NAME, null , null);
        Log.w(TAG, Integer.toString(doneDelete));

        return doneDelete > 0;
    }

    public void modifier(Integer id) {
        // CODE
    }


    public Cursor fetchAllScores() {

        Cursor mCursor = mDb.query(TABLE_NAME, new String[] {_ID,
                        NOM, SCORE},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor fetchScoreByScore(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;

        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(TABLE_NAME, new String[] {_ID,
                            NOM, SCORE},
                    null, null, null, null, null);
        }else {
            mCursor = mDb.query(true, TABLE_NAME, new String[] {_ID,
                            NOM, SCORE},
                    SCORE + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    public Integer selectBestSCore() {

        Integer score = 0;

        // CODE
        return score;
    }

    public Cursor getScore_data() {
        return mDb.rawQuery( "SELECT " + _ID + ", " + NOM + ", " + SCORE + " FROM " + TABLE_NAME , null);
    }
}
