package com.example.laba4.database.helper_models;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Workers.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS worker (\n" + "    workerid integer PRIMARY KEY AUTOINCREMENT,\n" + "    worker_name character(100),\n" + "    age integer);\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public void delete(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }
}
