package de.philipplange.stueckpreisrechner_neu;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private SQLiteDatabase database;
    private DbHelper dbHelper;

    private String[] columns = {
            DbHelper.COLUMN_NAME,
            DbHelper.COLUMN_MENGE,
            DbHelper.COLUMN_EINHEIT,
            DbHelper.COLUMN_PREIS
    };

    public DataSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addZutat(Zutat z) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, z.getName());
        values.put(DbHelper.COLUMN_MENGE, z.getMenge());
        values.put(DbHelper.COLUMN_EINHEIT, z.getEinheit().toString());
        values.put(DbHelper.COLUMN_PREIS, z.getPreis());

        database.insert(DbHelper.TABLE_ZUTATEN, null, values);
    }

    private Zutat cursorToZutat(Cursor cursor) {
        int idName = cursor.getColumnIndex(DbHelper.COLUMN_NAME);
        int idMenge = cursor.getColumnIndex(DbHelper.COLUMN_MENGE);
        int idEinheit = cursor.getColumnIndex(DbHelper.COLUMN_EINHEIT);
        int idPreis = cursor.getColumnIndex(DbHelper.COLUMN_PREIS);

        String name = cursor.getString(idName);
        double menge = cursor.getDouble(idMenge);
        String einheit = cursor.getString(idEinheit);
        double preis = cursor.getDouble(idPreis);


        Zutat zutat = new Zutat(name, menge, Einheit.valueOf(einheit.toUpperCase()), preis);
        return zutat;
    }

    public List<Zutat> getAllZutaten() {
        List<Zutat> zutatenListe = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_ZUTATEN, columns, null, null, null, null, null);
        cursor.moveToFirst();
        Zutat zutat;

        while (!cursor.isAfterLast()) {
            zutat = cursorToZutat(cursor);
            zutatenListe.add(zutat);
            cursor.moveToNext();
        }
        cursor.close();
        return zutatenListe;
    }
}
