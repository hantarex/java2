package ru.sportdepo.shikov.lesson21;

import android.database.sqlite.SQLiteDatabase;

public class ContentTable {
    private static final String TABLE_NAME = "content";
    private static final String ID = "id";
    private static final String NAME_COLUMN = "name";
    private static final String SORT_COLUMN = "sort";
    private static final String LINK_COLUMN = "link";
    private static final String JSON_COLUMN = "json";

    public static void createTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_NAME + " (" +
                ID + " not null auto_increment key," +
                TABLE_NAME + "_type int," +
                NAME_COLUMN + " text," +
                SORT_COLUMN + " int," +
                LINK_COLUMN + " text" +
                JSON_COLUMN + " text");
    }

}
