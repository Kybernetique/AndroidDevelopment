package com.example.mobilelabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobilelabs.storage.DBBossStorage;
import com.example.mobilelabs.storage.FileBossStorage;
import com.example.mobilelabs.storage.BossStorage;
import com.example.mobilelabs.storage.Boss;
import com.example.mobilelabs.storage.IBossStorage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchResultsActivity extends AppCompatActivity {

    ArrayList<Boss> bosses;
    ArrayAdapter<Boss> adapter;
    IBossStorage bossesStorage = new BossStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Button buttonSelectAll = findViewById(R.id.buttonSelectAll);
        Button buttonDeselectAll = findViewById(R.id.buttonDeselectAll);
        Button buttonPrintAll = findViewById(R.id.buttonPrintAll);
        Button buttonBack = findViewById(R.id.buttonBack);

        Intent ownIntent = getIntent();
        String bossName = ownIntent.getStringExtra("bossName");
        SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
        if (sPref.contains("saveMode")){
            String saveMode = sPref.getString("saveMode", "");
            if (Objects.equals(saveMode, "DB")){
                bossesStorage = new DBBossStorage(this);
            }
            if (Objects.equals(saveMode, "Files")){
                bossesStorage = new FileBossStorage(this);
            }
        }
        else{
            bossesStorage = new FileBossStorage(this);
        }
/*
        bosses = (ArrayList<Boss>) bossesStorage.findSimilar(bosses.stream().filter(value -> value.(bossName)).collect(Collectors.toList());
*/
        bosses = (ArrayList<Boss>) bossesStorage.findSimilar(new Boss(bossName));

        ListView listViewResult = findViewById(R.id.listViewResult);


        buttonSelectAll.setOnClickListener(view -> {
            try {
                if (!bosses.isEmpty()) {

                    for (int i = 0; i < bosses.size(); i++) {
                        listViewResult.setItemChecked(i, true);
                    }
                }
                else {
                    Toast.makeText(SearchResultsActivity.this, "List is empty!", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception ex) {
                Toast.makeText(SearchResultsActivity.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });

        buttonDeselectAll.setOnClickListener(view -> {
            try {
                if (!bosses.isEmpty()) {
                    for (int i = 0; i < bosses.size(); i++) {
                        listViewResult.setItemChecked(i, false);

                    }
                }
                else {
                    Toast.makeText(SearchResultsActivity.this, "List is empty!", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception ex) {
                Toast.makeText(SearchResultsActivity.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });

        buttonPrintAll.setOnClickListener(view -> {
            String result;
            SparseBooleanArray sbArray = listViewResult.getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key)) {
                    result = bosses.get(i).toString();
                    Toast.makeText(SearchResultsActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new ArrayAdapter<Boss>(this, R.layout.forlistview, bosses);
        listViewResult.setAdapter(adapter);
        listViewResult.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
}