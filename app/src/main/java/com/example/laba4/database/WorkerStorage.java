package com.example.laba4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.laba4.MainActivity;
import com.example.laba4.database.helper_models.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class WorkerStorage implements IWorkerStorage {

    DBHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "worker";
    final String COLUMN_ID = "workerid";
    final String COLUMN_NAME = "worker_name";
    final String COLUMN_AGE = "age";

    public WorkerStorage(Context context) {
        sqlHelper = new DBHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public WorkerStorage open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    @Override
    public List<Worker> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<Worker> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Worker obj = new Worker();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.age = cursor.getInt((int) cursor.getColumnIndex(COLUMN_AGE));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
            list.add(obj);
            cursor.moveToNext();
        }
        while (!cursor.isAfterLast());
        return list;
    }

    @Override
    public List<Worker> getFilteredList(Worker model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<Worker> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Worker obj = new Worker();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.age = cursor.getInt((int) cursor.getColumnIndex(COLUMN_AGE));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
            list.add(obj);
            cursor.moveToNext();
        }
        while (!cursor.isAfterLast());
        return list;
    }

    @Override
    public Worker getElement(Worker model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where " + COLUMN_ID + " = " + model.id, null);
        Worker obj = new Worker();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.age = cursor.getInt((int) cursor.getColumnIndex(COLUMN_AGE));
        obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        return obj;
    }

    @Override
    public void create(Worker model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_AGE, model.age);
        content.put(COLUMN_NAME, model.name);
        db.insert(TABLE, null, content);
    }

    @Override
    public void update(Worker model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_ID, model.id);
        content.put(COLUMN_NAME, model.name);
        content.put(COLUMN_AGE, model.age);
        String where = COLUMN_ID + " = " + model.id;
        db.update(TABLE, content, where, null);
    }

    @Override
    public void delete(Worker model) {
        String where = COLUMN_ID + " = " + model.id;
        db.delete(TABLE, where, null);
    }

    @Override
    public void delete() {
        db.delete(TABLE, null, null);
    }

    public int getElementCount() {
        return (int) DatabaseUtils.queryNumEntries(db, TABLE);
    }


}
