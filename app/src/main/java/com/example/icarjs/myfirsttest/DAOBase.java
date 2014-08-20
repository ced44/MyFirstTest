package com.example.icarjs.myfirsttest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by imie on 19/08/14.
 */
public abstract class DAOBase {
    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 1;

    // Le nom du fichier qui représente ma base
    protected final static String NOM = "database.db";

    protected SQLiteDatabase mDb = null;
    protected DataBaseScore mHandler = null;

    public DAOBase(Context pContext) {
        this.mHandler = new DataBaseScore(pContext, NOM, null, VERSION);
        Log.d("DAOBase","constructeur");
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();
        Log.d("DAOBase","open()");
        return mDb;
    }

    public void close() {

        mDb.close();
        Log.d("DAOBase","close()");

    }

    public SQLiteDatabase getDb() {
        Log.d("DAOBase","get()");
        return mDb;
    }
}
