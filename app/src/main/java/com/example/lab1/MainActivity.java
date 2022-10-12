package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listOfBosses;
    ListView listViewOfBosses;
    EditText editTextBossFullName;

    Button buttonAdd;
    Button buttonSelectAll;
    Button buttonDeselectAll;
    Button buttonPrintAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSelectAll = findViewById(R.id.button1);
        buttonDeselectAll = findViewById(R.id.button2);
        buttonPrintAll = findViewById(R.id.button3);

        listOfBosses = new ArrayList<>();
        listViewOfBosses = findViewById(R.id.lv); // получили доступ к listview
        editTextBossFullName = findViewById(R.id.editTextBossFullName);


        buttonAdd.setOnClickListener(view -> {
            try {
                if (!listOfBosses.contains(editTextBossFullName.getText().toString())) {
                    listOfBosses.add(editTextBossFullName.getText().toString());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                            R.layout.forlist, listOfBosses); // то, как данные будут храниться
                    listViewOfBosses.setAdapter(adapter);
                    listViewOfBosses.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                } else {
                    Toast.makeText(MainActivity.this, "The element already exists!",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex) {
                Toast.makeText(MainActivity.this, "Error!",
                        Toast.LENGTH_LONG).show();
            }
        });


        buttonSelectAll.setOnClickListener(view -> {
            try {
                if (!listOfBosses.isEmpty()) {
                    for (int i = 0; i < listOfBosses.size(); i++) {
                        if (!listOfBosses.get(i).isEmpty()) {
                            listViewOfBosses.setItemChecked(i, true);
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "List is empty!",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex ) {
                Toast.makeText(MainActivity.this, "Error!",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonDeselectAll.setOnClickListener(view -> {
            try {
                if (!listOfBosses.isEmpty()) {
                    for (int i = 0; i < listOfBosses.size(); i++) {
                        if (!listOfBosses.get(i).isEmpty()) {
                            listViewOfBosses.setItemChecked(i, false);
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "List is empty!",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex ) {
                Toast.makeText(MainActivity.this, "Error!",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonPrintAll.setOnClickListener(view -> {
            String result;
            SparseBooleanArray sbArray = listViewOfBosses.getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key)) {
                    result= listOfBosses.get(i);
                    Toast.makeText(MainActivity.this, result,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}