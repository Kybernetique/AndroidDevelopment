package com.example.laba4;

import static android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.laba4.database.WorkerStorage;
import com.example.laba4.database.helper_models.JSONHelper;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Implementation of App Widget functionality.
 */
public class MyWidget extends AppWidgetProvider {

    final static String LOG_TAG = "myLogs";
    private static WorkerStorage storage;

    public static String REFRESH_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";
    static private RemoteViews views;

/*    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equalsIgnoreCase(REFRESH_ACTION)) {
            views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MyWidget.class));

            views.setTextViewText(R.id.tv, "Количество элементов в бд: " + storage.getElementCount());
            appWidgetManager.updateAppWidget(appWidgetIds, views);
        }
    }*/

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int id : appWidgetIds) {
            updateWidget(context, appWidgetManager, id);
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }

    static void updateWidget(Context context, AppWidgetManager appWidgetManager, int widgetID) {
/*        if (storage == null) {
            storage = new WorkerStorage(context);
        }

        CharSequence widgetText = "Количество записей в бд: " + storage.getElementCount();

        // Настраиваем внешний вид виджета
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        views.setTextViewText(R.id.tv, widgetText);

        Intent updateIntent = new Intent(context, MyWidget.class);

        updateIntent.setAction(ACTION_APPWIDGET_UPDATE);
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{widgetID});

        PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, updateIntent, PendingIntent.FLAG_IMMUTABLE);
        *//*      updateIntent.setAction(pIntent);*//*

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(widgetID, views);*/
        if (storage == null) {
            storage = new WorkerStorage(context);
        }
        CharSequence widgetText = "Количество записей в бд: " + storage.getElementCount();

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        views.setTextViewText(R.id.tv, widgetText);

        Intent intent = new Intent(context, MyWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, MyWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);

        PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, intent, PendingIntent.FLAG_IMMUTABLE);
        views.setPendingIntentTemplate(R.id.tv, pIntent);

        context.sendBroadcast(intent);


/*        // Настраиваем внешний вид виджета
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        views.setTextViewText(R.id.tv, widgetText);

        Intent updateIntent = new Intent(context, MyWidget.class);
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, MyWidget.class));

        PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, updateIntent, PendingIntent.FLAG_IMMUTABLE);
        views.setPendingIntentTemplate(R.id.tv, pIntent);

        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(updateIntent);*/

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(widgetID, views);
    }


/*    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
*//*
        CharSequence widgetText = "Количество записей в бд: " + storage.getElementCount();

        if (intent != null) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals(ACTION_APPWIDGET_UPDATE)) {
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MyWidget.class));
                    for (int id : appWidgetIds) {
                        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
                        views.setTextViewText(R.id.tv, widgetText);
                        appWidgetManager.updateAppWidget(id, views);
                    }
                }
            }
        }*//*

    }*/

}