package com.example.laba4;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba4.database.Worker;
import com.example.laba4.database.WorkerStorage;
import com.example.laba4.database.helper_models.DBHelper;
import com.example.laba4.database.helper_models.JSONHelper;
import com.example.laba4.database.service.DBService;
import com.example.laba4.database.service.IService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {
    private ArrayAdapter<Worker> adapter;

    UpdateWorkerFragment updateWorkerFragment;

    private EditText nameText, ageText;
    ListView listView;
    Button buttonCreate;
    Button buttonRead;
    Button buttonUpdate;
    Button buttonDelete;

    SharedPreferences sPref;
    public static List<Worker> workers;
    WorkerStorage workerStorage;
    DBHelper dbHelper;
    IService dbService;
    Intent serviceIntent;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        syncDatabases();
    }

    public void loadData() {
        sPref = getPreferences(MODE_PRIVATE);

        nameText = (EditText) findViewById(R.id.nameText);
        ageText = (EditText) findViewById(R.id.ageText);

        listView = (ListView) findViewById(R.id.list);
        buttonCreate = findViewById(R.id.buttonCreate);
        buttonRead = findViewById(R.id.buttonRead);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);


        workers = new ArrayList<Worker>();
        dbHelper = new DBHelper(getBaseContext());
        workerStorage = new WorkerStorage(getBaseContext());
        serviceIntent = new Intent(this, DBService.class);

        serviceConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                dbService = (IService) binder;
            }

            public void onServiceDisconnected(ComponentName name) {
            }
        };
        this.startService(serviceIntent);
        this.bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);


        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                int age = Integer.parseInt(ageText.getText().toString());
                if (!dbService.getList().contains(name)) {
                    Worker worker = new Worker(name);
                    worker.age = age;
                    workers.add(worker);
                    dbService.updateWidget();
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "The element already exists!", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
/*
                int age = Integer.parseInt(ageText.getText().toString());
*/

                Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                intent.putExtra("workerName", name);
/*
                intent.putExtra("workerAge", age);
*/
                startActivity(intent);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateWorkerFragment = new UpdateWorkerFragment();
                Bundle bundle = new Bundle();
                String workerName = "";
                int workerAge = 0;
                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
                for (int i = 0; i < listView.getCount(); i++) {
                    if (sparseBooleanArray.get(i)) {
                        workerName = adapter.getItem(i).name;
                        workerAge = adapter.getItem(i).age;
                    }
                }
                bundle.putString("workerName", workerName);
                bundle.putInt("workerAge", workerAge);
                adapter.notifyDataSetChanged();
                dbService.updateWidget();
                updateWorkerFragment.setArguments(bundle);
                updateWorkerFragment.show(getSupportFragmentManager(), "updateWorker");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkboxDB = findViewById(R.id.checkboxDB);
                if (checkboxDB.isChecked()) {
                    SparseBooleanArray parsedList = listView.getCheckedItemPositions();
                    for (int i = parsedList.size() - 1; i >= 0; i--) {
                        if (parsedList.valueAt(i)) {
                            adapter.remove(adapter.getItem(parsedList.keyAt(i)));
                        }
                    }

                    for (int i = 0; i < listView.getCount(); i++) {
                        listView.setItemChecked(i, false);
                    }
                }
                else {
                    SparseBooleanArray parsedList = listView.getCheckedItemPositions();

                    for (int i = listView.getCount() - 1; i >= 0; i--) {
                        if (parsedList.indexOfKey(i) < 0) {
                            adapter.remove(adapter.getItem(i));
                        }
                        else if (!parsedList.valueAt(i)) {
                            adapter.remove(adapter.getItem(i));
                        }
                    }

                    for (int i = 0; i < listView.getCount(); i++) {
                        listView.setItemChecked(i, true);
                    }
                }
                adapter.notifyDataSetChanged();
                dbService.updateWidget();
            }
        });

        adapter = new ArrayAdapter<>(this, R.layout.forlistview, workers);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
    }

    public void exportToJSON(View view) {
        CheckBox checkboxDB = findViewById(R.id.checkboxDB);
        if (checkboxDB.isChecked()) {
            for (Worker worker : workers) {
                if (worker.id != 0) {
                    // workerStorage.update(worker);
                    dbService.update(worker);
                }
                else {
                    // workerStorage.create(worker);
                    dbService.create(worker);
                }
            }
            syncDatabases();
        }
        else {
            Runnable runnable = new Runnable() {
                Context context = getBaseContext();

                @Override
                public void run() {
                    JSONHelper.exportToJSON(context, workers);
                }
            };
            Thread thread = new Thread(runnable);
            // Запускаем поток
            thread.start();
            syncDatabases();
        }
    }

    public void importFromJSON(View view) {
        CheckBox checkboxFoulder = findViewById(R.id.checkboxDB);
        if (checkboxFoulder.isChecked()) {
            // List<Worker> workers = workerStorage.getFullList();
            List<Worker> workers = dbService.getList();
            this.workers = workers;
            if (this.workers != null) {
                adapter = new ArrayAdapter<>(this, R.layout.forlistview, this.workers);
                listView.setAdapter(adapter);
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Runnable runnable = new Runnable() {
                Context context = getBaseContext();

                @Override
                public void run() {
                    workers = JSONHelper.importFromJSON(context);
                    adapter = new ArrayAdapter<>(context, R.layout.forlistview, workers);
                    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    listView.post(new Runnable() {
                        public void run() {
                            listView.setAdapter(adapter);
                        }
                    });

                }
            };
            Thread thread = new Thread(runnable);
            // Запускаем поток
            thread.start();
        }
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        String workerName = updateWorkerFragment.resultWorkerName;
        int workerAge = updateWorkerFragment.resultWorkerAge;
        if (workerName != null) {
            SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
            for (int i = 0; i < listView.getCount(); i++) {
                if (sparseBooleanArray.get(i) == true) {
                    workers.get(i).name = workerName;
                    workers.get(i).age = workerAge;
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void syncDatabases() {
        Context context = getBaseContext();
        workerStorage = new WorkerStorage(context);
        List<Worker> workersDB = workerStorage.getFullList();

        List<Worker> workersJSON = JSONHelper.importFromJSON(context);
        if (workersJSON != null)
            for (Worker worker : workersJSON) {
                worker.id = 0;
            }

        if (workersJSON != null)
            for (Worker worker : workersJSON) {
                boolean isExist = false;
                for (Worker wrkr : workersDB) {
                    if (wrkr.age == worker.age) {
                        if (wrkr.name.equals(worker.name)) {
                            isExist = true;
                            break;
                        }
                    }

                }
                if (!isExist)
                    workersDB.add(worker);
            }

        for (Worker worker : workersDB) {
            if (worker.id != 0) {
                workerStorage.update(worker);
            }
            else
                workerStorage.create(worker);
        }

        JSONHelper.exportToJSON(context, workersDB);
    }

    /*public void delete(View view) {
        CheckBox checkboxFoulder = findViewById(R.id.checkboxFoulder);
        if (checkboxFoulder.isChecked()) {
            dbHelper.delete(getBaseContext());
        } else {
            JSONHelper.deleteFile(getBaseContext());
        }
    }*/

/*    public void savePrefs(View view) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setWorkers(workers);
        String jsonString = gson.toJson(dataItems);
        Editor prefsEditor = sPref.edit();
        prefsEditor.putString("MyObject", jsonString);
        prefsEditor.commit();
        Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
    }*/

/*    public void loadPrefs(View view) {
        Gson gson = new Gson();
        String json = sPref.getString("MyObject", "");
        DataItems obj = gson.fromJson(json, DataItems.class);
        workers = obj.getWorkers();
        if (workers != null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workers);
            listView.setAdapter(adapter);
            Toast.makeText(this, "Данные восстановлены Prefs", Toast.LENGTH_LONG).show();
        }
    }*/
}