package com.example.mobilelabs;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilelabs.service.DBService;
import com.example.mobilelabs.service.IService;
import com.example.mobilelabs.storage.Boss;
import com.example.mobilelabs.storage.DBBossStorage;
import com.example.mobilelabs.storage.FileBossStorage;
import com.example.mobilelabs.storage.IBossStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    static IBossStorage bossesStorage;
    ArrayAdapter<Boss> adapter;
    ListView listViewBosses;
    UpdateBossFragment bossEditFragment;

    IService dbService;
    Intent serviceIntent;
    private ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent = new Intent(this, DBService.class);
        conn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                dbService = (IService) binder;
            }

            public void onServiceDisconnected(ComponentName name) {
            }
        };
        this.startService(serviceIntent);
        this.bindService(serviceIntent, conn, BIND_AUTO_CREATE);

        {
            DBBossStorage dbBosses = new DBBossStorage(this);
            FileBossStorage fileBosses = new FileBossStorage(this);
            List<Boss> dbBoss = dbBosses.getList();
            List<Boss> fileBoss = fileBosses.getList();
            List<Boss> inDb = new ArrayList<>();
            List<Boss> inFile = new ArrayList<>();

            for (int i = 0; i < fileBoss.size(); ++i) {
                boolean fl = false;
                for (int j = 0; j < dbBoss.size(); ++j) {
                    if (Objects.equals(fileBoss.get(i).name, dbBoss.get(j).name)) {
                        fl = true;
                    }
                }
                if (!fl) {
                    inDb.add(fileBoss.get(i));
                }
            }
            for (int i = 0; i < dbBoss.size(); ++i) {
                boolean fl = false;
                for (int j = 0; j < fileBoss.size(); ++j) {
                    if (Objects.equals(fileBoss.get(j).name, dbBoss.get(i).name)) {
                        fl = true;
                    }
                }
                if (!fl) {
                    inFile.add(dbBoss.get(i));
                }
            }
            for (int i = 0; i < inDb.size(); ++i) {
                dbBosses.add(inDb.get(i));
            }
            for (int i = 0; i < inFile.size(); ++i) {
                fileBosses.add(inFile.get(i));
            }
        }

        SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
        if (sPref.contains("saveMode")) {
            String saveMode = sPref.getString("saveMode", "");
            if (Objects.equals(saveMode, "DB")) {
                bossesStorage = new DBBossStorage(this);
            }
            if (Objects.equals(saveMode, "Files")) {
                bossesStorage = new FileBossStorage(this);
            }
        }
        else {
            bossesStorage = new FileBossStorage(this);
        }

        listViewBosses = findViewById(R.id.listViewBosses);
        TextView editTextBossFullName = findViewById(R.id.editTextBossFullName);
        Button buttonCreate = findViewById(R.id.buttonCreate);
        Button buttonRead = findViewById(R.id.buttonRead);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        buttonCreate.setOnClickListener(v -> {
            if (dbService.getList().contains(editTextBossFullName.getText().toString())) {
                String addedText = editTextBossFullName.getText().toString();
                dbService.create(new Boss(addedText));
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this, "The element already exists!", Toast.LENGTH_LONG).show();

            }
        });

        buttonDelete.setOnClickListener(v -> {
            SparseBooleanArray sparseBooleanArray = listViewBosses.getCheckedItemPositions();
            int len = listViewBosses.getCount();
            for (int i = 0; i < len; i++) {
                if (sparseBooleanArray.get(i) == true) {
                    dbService.delete(adapter.getItem(i));
                }
            }
            listViewBosses.clearChoices();
            adapter.notifyDataSetChanged();
        });

        buttonRead.setOnClickListener(v -> {
            String similar = editTextBossFullName.getText().toString();
            Intent intent = new Intent(this, SearchResultsActivity.class);
            intent.putExtra("bossName", similar);
            startActivity(intent);
        });

        buttonUpdate.setOnClickListener(v -> {
            bossEditFragment = new UpdateBossFragment();
            Bundle bundle = new Bundle();
            String boss = new String();
            SparseBooleanArray sparseBooleanArray = listViewBosses.getCheckedItemPositions();
            for (int i = 0; i < listViewBosses.getCount(); i++) {
                if (sparseBooleanArray.get(i) == true) {
                    boss = adapter.getItem(i).name;
                }
            }
            bundle.putString("bossName", boss);
            bossEditFragment.setArguments(bundle);
            bossEditFragment.show(getFragmentManager(), "updateBoss");
        });

        Button buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });

        adapter = new ArrayAdapter<Boss>(this, R.layout.forlistview, dbService.getList());
        listViewBosses.setAdapter(adapter);
        listViewBosses.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        String boss = bossEditFragment.resultBossName;
        if (boss != null) {
            SparseBooleanArray sparseBooleanArray = listViewBosses.getCheckedItemPositions();
            for (int i = 0; i < listViewBosses.getCount(); i++) {
                if (sparseBooleanArray.get(i) == true) {
                    Boss pr = new Boss(boss);
                    pr.id = adapter.getItem(i).id;
                    dbService.update(pr);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}





        /*
        buttonOutputSelected.setOnClickListener(v -> {
            SparseBooleanArray sparseBooleanArray  = listViewProducts.getCheckedItemPositions();
            String checked = "";
            for(int i = 0; i < listViewProducts.getCount(); i++)
            {
                if(sparseBooleanArray.get(i) == true)
                {
                    checked += listViewProducts.getItemAtPosition(i).toString() + "\n";
                }
            }
            Toast.makeText(this, checked, Toast.LENGTH_LONG).show();
        });

        buttonChoseLast.setOnClickListener(v -> {
            if (listViewProducts.getCount() > 0) {
                listViewProducts.setItemChecked(listViewProducts.getCount() - 1, true);
            }
        });

        buttonResetSelection.setOnClickListener(v -> {
            listViewProducts.clearChoices();
            adapter.notifyDataSetChanged();
        });
        */
