package de.philipplange.stueckpreisrechner_neu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "stueckpreisrechner.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_ZUTATEN = "zutaten";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MENGE = "menge";
    public static final String COLUMN_EINHEIT = "einheit";
    public static final String COLUMN_PREIS = "preis";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ZUTATEN +
                    "(" + COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_MENGE + " REAL NOT NULL, " +
                    COLUMN_EINHEIT + " TEXT NOT NULL," +
                    COLUMN_PREIS + " REAL NOT NULL );";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE);
        } catch (Exception e) {
            Log.d("FEHLER", "Fehler beim Erstellen der Datenbank." + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
