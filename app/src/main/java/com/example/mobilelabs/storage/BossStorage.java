package com.example.mobilelabs.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BossStorage implements IBossStorage {
    DBHelper sqlHelper;
    SQLiteDatabase db;
    private static ArrayList<Boss> Bosses;

    public BossStorage(){
        Bosses = new ArrayList<Boss>();
    }

    public BossStorage(Context context) {
        sqlHelper = new DBHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    @Override
    public List<Boss> getList() {
        return Bosses;
    }

    @Override
    public void add(Boss Boss) {
        int id = 0;
        for (int i = 0; i < Bosses.size(); ++i){
            if (id <= Bosses.get(i).id){
                id = Bosses.get(i).id + 1;
            }
        }
        Boss.id = id;
        Bosses.add(Boss);
    }

    @Override
    public void update(Boss Boss) {
        for (int i = 0; i < Bosses.size(); ++i){
            if (Bosses.get(i).id == Boss.id){
                Bosses.get(i).name = Boss.name;
            }
        }
    }

    @Override
    public void delete(Boss Boss) {
        int index = -1;
        for (int i = 0; i < Bosses.size(); ++i){
            if (Bosses.get(i).id == Boss.id){
                index = i;
            }
        }
        if (index != -1){
            Bosses.remove(index);
        }
    }

    @Override
    public ArrayList<Boss> findSimilar(Boss Boss) {
        ArrayList<Boss> similarBosses = new ArrayList<Boss>();
        for (int i = 0; i < Bosses.size(); ++i){
            if (Bosses.get(i).name.contains(Boss.name)){
                similarBosses.add(Bosses.get(i));
            }
        }
        return similarBosses;
    }
}
