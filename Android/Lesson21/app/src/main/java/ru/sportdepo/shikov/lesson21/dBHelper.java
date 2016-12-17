package ru.sportdepo.shikov.lesson21;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dBHelper extends SQLiteOpenHelper {

    private static final String LESSON21_DB = "lesson21.db";
    private static final int VERSION = 1;

    public dBHelper(Context context) {
        super(context, LESSON21_DB, null, VERSION);
        Log.e("DB","Create DB");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("DB","Start create table");
        ContentTable.createTable(sqLiteDatabase);
        Log.d("DB","Create table!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
