package com.example.laba4;

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

import com.example.laba4.R;
import com.example.laba4.database.WorkerStorage;
import com.example.laba4.database.Worker;
import com.example.laba4.database.service.IService;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchResultsActivity extends AppCompatActivity {
/*
    ArrayList<Worker> bosses;
    ArrayAdapter<Worker> adapter;
    IService bossesStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Button buttonSelectAll = findViewById(R.id.buttonSelectAll);
        Button buttonDeselectAll = findViewById(R.id.buttonDeselectAll);
        Button buttonPrintAll = findViewById(R.id.buttonPrintAll);
        Button buttonBack = findViewById(R.id.buttonBack);

        Intent ownIntent = getIntent();
        String workerName = ownIntent.getStringExtra("workerName");

*//*
        bosses = (ArrayList<Boss>) bossesStorage.findSimilar(bosses.stream().filter(value -> value.(workerName)).collect(Collectors.toList());
*//*
        bosses = (ArrayList<Worker>) bossesStorage.findSimilar(new Worker(workerName));

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
    }*/
}