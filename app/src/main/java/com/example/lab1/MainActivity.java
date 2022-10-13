package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonSelectAll;
    Button buttonDeselectAll;
    Button buttonPrintAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentCUD fragmentCUD = new FragmentCUD();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.FragmentContainer, fragmentCUD);
        fragmentTransaction.commit();


/*        buttonSelectAll.setOnClickListener(view -> {
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
            } catch (Exception ex) {
                Toast.makeText(MainActivity.this, "Error!",
                        Toast.LENGTH_LONG).show();
            }
        });*/

/*        buttonDeselectAll.setOnClickListener(view -> {
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
            } catch (Exception ex) {
                Toast.makeText(MainActivity.this, "Error!",
                        Toast.LENGTH_LONG).show();
            }
        });*/

/*
        buttonPrintAll.setOnClickListener(view -> {
            String result;
            SparseBooleanArray sbArray = listViewOfBosses.getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key)) {
                    result = listOfBosses.get(i);
                    Toast.makeText(MainActivity.this, result,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}