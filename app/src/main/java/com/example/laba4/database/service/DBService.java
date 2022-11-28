package com.example.laba4.database.service;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.example.laba4.MainActivity;
import com.example.laba4.MyWidget;
import com.example.laba4.R;
import com.example.laba4.database.Worker;
import com.example.laba4.database.WorkerStorage;

import java.util.ArrayList;
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
    public void onCreate() {
        storage = new WorkerStorage(this);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void create(Worker worker) {
        if (worker.id != 0) {
            storage.update(worker);
            return;
        }
        else
            storage.create(worker);
    }

    public void update(Worker model) {
        storage.update(model);
    }

    public List<Worker> getList() {
        List<Worker> workers = storage.getFullList();
        return workers;
    }

    public int getCount() {
        return storage.getElementCount();
    }

    public void delete(Worker worker) {
        storage.delete(worker);
    }

    class MyBinder extends Binder implements IService {
        @Override
        public void create(Worker worker) {
            DBService.this.create(worker);
        }

        @Override
        public void update(Worker model) {
            DBService.this.update(model);
        }

        @Override
        public List<Worker> getList() {
            return DBService.this.getList();
        }

        @Override
        public void delete(Worker worker) {
            DBService.this.delete(worker);
        }

        @Override
        public int getCount() {
            return DBService.this.getCount();
        }

        @Override
        public void updateWidget() {
            Intent intent = new Intent(DBService.this, MyWidget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
            // since it seems the onUpdate() is only fired on that:
            int[] ids = AppWidgetManager.getInstance(getApplication())
                    .getAppWidgetIds(new ComponentName(getApplication(), MyWidget.class));
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
            sendBroadcast(intent);
        }
    }
}