package com.example.mobilelabs.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DBBossStorage implements IBossStorage {
    DBHelper dbHelper;
    private ArrayList<Boss> Bosses = new ArrayList<Boss>();

    public DBBossStorage(Context context){
/*
        context.deleteDatabase("myDB");
*/
        dbHelper = new DBHelper(context);
        readAll();
    }
    @Override
    public List<Boss> getList() {
        return Bosses;
    }

    @Override
    public void add(Boss Boss) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", Boss.name);
        long rowID = db.insert("bosses", null, cv);
        Boss.id = (int) rowID;
        Bosses.add(Boss);
        dbHelper.close();
    }

    @Override
    public void update(Boss Boss) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", Boss.name);
        db.update("bosses", cv, "id = ?", new String[] {String.valueOf(Boss.id)});
        dbHelper.close();
        readAll();
    }

    @Override
    public void delete(Boss Boss) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", Boss.name);
        db.delete("bosses", "id = " + Boss.id, null);
        dbHelper.close();
        readAll();
    }

    @Override
    public List<Boss> findSimilar(Boss Boss) {
        ArrayList<Boss> Bosses = new ArrayList<Boss>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("bosses", null, "name = ?", new String[] {Boss.name}, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            do {
                Boss pr = new Boss();
                pr.id = c.getInt(idColIndex);
                pr.name = c.getString(nameColIndex);
                Bosses.add(pr);
            } while (c.moveToNext());
        }
        dbHelper.close();
        return Bosses;
    }


    public void readAll(){
        Bosses.clear();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("bosses", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            do {
                Boss pr = new Boss();
                pr.id = c.getInt(idColIndex);
                pr.name = c.getString(nameColIndex);
                Bosses.add(pr);
            } while (c.moveToNext());
        }
        dbHelper.close();
    }

    private void importFromJSON(Context context, String fileName) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try(FileInputStream fileInputStream = context.openFileInput(fileName);
                    InputStreamReader streamReader = new InputStreamReader(fileInputStream)){
                    Gson gson = new Gson();
                    DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
                    Bosses = (ArrayList<Boss>) dataItems.getBosses();
                    for (int i = 0; i < Bosses.size(); ++i){
                        add(Bosses.get(i));
                    }
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private static class DataItems {
        private List<Boss> Bosses;

        List<Boss> getBosses() {
            return Bosses;
        }
        void setBosses(List<Boss> Bosses) {
            this.Bosses = Bosses;
        }
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
/*
            db.execSQL("DROP TABLE IF EXISTS bosses");
*/
            db.execSQL("create table bosses ("
                    + "id integer primary key autoincrement,"
                    + "name text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public int BossCount(){
        return Bosses.size();
    }
}
