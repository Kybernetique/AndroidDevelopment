package com.example.mobilelabs.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.mobilelabs.storage.Boss;
import com.example.mobilelabs.storage.BossStorage;

import java.util.List;

public class DBService extends Service {

    BossStorage storage;

    public DBService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate()
    {
        storage = new BossStorage(this);
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    public void create(Boss boss){
        if(boss.id!=0){
            storage.update(boss);
            return;
        }
        else storage.add(boss);
    }

    public void update(Boss boss){
        storage.update(boss);
    }

    public List<Boss> getList(){
        List<Boss> bosses = storage.getList();
        return bosses;
    }

    public void delete(Boss boss){
        storage.delete(boss);
    }

    class MyBinder extends Binder implements IService
    {
        @Override
        public void create(Boss boss){
            DBService.this.create(boss);
        }

        @Override
        public void update(Boss boss){
            DBService.this.update(boss);
        }

        @Override
        public List<Boss> getList(){
            return DBService.this.getList();
        }

        @Override
        public void delete(Boss boss){
            DBService.this.delete(boss);
        }
    }
}
