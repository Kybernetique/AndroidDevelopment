package com.example.laba4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.laba4.database.Worker;
import com.example.laba4.database.service.IService;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SearchResultsActivity extends AppCompatActivity {
    ArrayList<Worker> workers;
    ArrayAdapter<Worker> adapter;
    IService workerStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
/*
        Button buttonSelectAll = findViewById(R.id.buttonSelectAll);
        Button buttonDeselectAll = findViewById(R.id.buttonDeselectAll);
        Button buttonPrintAll = findViewById(R.id.buttonPrintAll);
        Button buttonBack = findViewById(R.id.buttonBack);

        Intent ownIntent = getIntent();
        String workerName = ownIntent.getStringExtra("workerName");

*//*
        workers = (ArrayList<Worker>) workerStorage.findSimilar(new Worker(workerName));
*//*

        ListView listViewResult = findViewById(R.id.listViewResult);


        buttonSelectAll.setOnClickListener(view -> {
            try {
                if (!workers.isEmpty()) {

                    for (int i = 0; i < workers.size(); i++) {
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
                if (!workers.isEmpty()) {
                    for (int i = 0; i < workers.size(); i++) {
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
                    result = workers.get(i).toString();
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

        adapter = new ArrayAdapter<Worker>(this, R.layout.forlistview, workers);
        listViewResult.setAdapter(adapter);
        listViewResult.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    */}
}