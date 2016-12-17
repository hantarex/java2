package ru.sportdepo.shikov.lesson21;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dBHelper extends SQLiteOpenHelper {

    private static final String LESSON21_DB = "lesson21.db";

    public dBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, LESSON21_DB, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
