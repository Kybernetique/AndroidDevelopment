package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    Button buttonBack;
    Button buttonSelectAll;
    Button buttonDeselectAll;
    Button buttonPrintAll;


    ListView listViewResult;
    ArrayList<String> listResult = BossFragment.output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonBack = findViewById(R.id.buttonBack);
        buttonSelectAll = findViewById(R.id.buttonSelectAll);
        buttonDeselectAll = findViewById(R.id.buttonDeselectAll);
        buttonPrintAll = findViewById(R.id.buttonPrintAll);
        listViewResult = findViewById(R.id.listViewResult);

        adapter = new ArrayAdapter<>(ResultActivity.this, R.layout.forlist, listResult); // то, как данные будут храниться
        listViewResult.setAdapter(adapter);
        listViewResult.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        buttonSelectAll.setOnClickListener(view -> {
            try {
                if (!listResult.isEmpty()) {

                    for (int i = 0; i < listResult.size(); i++) {
                        if (!listResult.get(i).isEmpty()) {
                            listViewResult.setItemChecked(i, true);
                        }
                    }
                }
                else {
                    Toast.makeText(ResultActivity.this, "List is empty!", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception ex) {
                Toast.makeText(ResultActivity.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });

        buttonDeselectAll.setOnClickListener(view -> {
            try {
                if (!listResult.isEmpty()) {
                    for (int i = 0; i < listResult.size(); i++) {
                        if (!listResult.get(i).isEmpty()) {
                            listViewResult.setItemChecked(i, false);
                        }
                    }
                }
                else {
                    Toast.makeText(ResultActivity.this, "List is empty!", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception ex) {
                Toast.makeText(ResultActivity.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });

        buttonPrintAll.setOnClickListener(view -> {
            String result;
            SparseBooleanArray sbArray = listViewResult.getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key)) {
                    result = listResult.get(i);
                    Toast.makeText(ResultActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}