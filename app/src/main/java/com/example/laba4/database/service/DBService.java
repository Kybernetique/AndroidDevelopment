package com.example.laba4.database.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.laba4.database.Worker;
import com.example.laba4.database.WorkerStorage;

import java.util.List;

public class DBService extends Service {

    WorkerStorage storage;

    public DBService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate()
    {
        System.out.println("onCreate");
        storage = new WorkerStorage(this);
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        System.out.println("onDestory");
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        System.out.println("onStart");
        super.onStart(intent, startId);
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    public void create(Worker worker){
        if(worker.id!=0){
            storage.update(worker);
            return;
        }
        else storage.create(worker);
    }

    public void update(Worker model){
        storage.update(model);
    }

    public List<Worker> getList(){
        List<Worker> workers = storage.getFullList();
        return workers;
    }

    public int getCount() {
        return storage.getElementCount();
    }

    public void delete(Worker worker){
        storage.delete(worker);
    }

    class MyBinder extends Binder implements IService
    {
        @Override
        public void create(Worker worker){
            DBService.this.create(worker);
        }

        @Override
        public void update(Worker model){
            DBService.this.update(model);
        }

        @Override
        public List<Worker> getList(){
            return DBService.this.getList();
        }

        @Override
        public void delete(Worker worker){
            DBService.this.delete(worker);
        }

        @Override
        public int getCount() {return DBService.this.getCount(); }
    }
}