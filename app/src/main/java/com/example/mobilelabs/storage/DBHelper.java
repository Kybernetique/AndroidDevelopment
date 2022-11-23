package com.example.mobilelabs.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int SCHEMA = 1; // версия базы данных

    public DBHelper(Context context) {
        super(context, "myDB", null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists bosses ("
                + "id integer primary key autoincrement,"
                + "name text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ "myDB");
        onCreate(db);
    }

    public void delete(Context context){
        context.deleteDatabase("myDB");
    }
}
