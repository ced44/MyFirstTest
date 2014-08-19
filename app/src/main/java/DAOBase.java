import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.icarjs.myfirsttest.DataBaseScore;

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
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
