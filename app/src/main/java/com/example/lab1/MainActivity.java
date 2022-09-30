package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    EditText et;
    Button buttonAdd;
    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editTextTextPersonName);
        buttonAdd = findViewById(R.id.buttonAdd);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        lv = (ListView) findViewById(R.id.lv); // получили доступ к listview
        List<String> list = new ArrayList<>();


        buttonAdd.setOnClickListener(view -> {
            try {
                if (!list.contains(et.getText().toString())) {
                    list.add(et.getText().toString());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                            R.layout.forlist, list); //то как данные будут храниться
                    lv.setAdapter(adapter);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                } else {
                    Toast.makeText(MainActivity.this, "The element already exists!",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex) {
                Toast.makeText(MainActivity.this, "Error!",
                        Toast.LENGTH_LONG).show();
            }
        });


        button1.setOnClickListener(view -> {
            try {
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (!list.get(i).isEmpty()) {
                            lv.setItemChecked(i, true);
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

        button2.setOnClickListener(view -> {
            try {
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (!list.get(i).isEmpty()) {
                            lv.setItemChecked(i, false);
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

        button3.setOnClickListener(view -> {
            String result;
            SparseBooleanArray sbArray = lv.getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key)) {
                    result= list.get(i);
                    Toast.makeText(MainActivity.this, result,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private List<String> initList() {
        List<String> list = new ArrayList<>();

        list.add("iPhone");
        list.add("HTC");
        list.add("Samsung");
        list.add("LG");

        return list;
    }
}