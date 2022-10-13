package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
/*    Button buttonSelectAll;
    Button buttonDeselectAll;
    Button buttonPrintAll;*/

    Button buttonRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRead = findViewById(R.id.buttonRead);

        FragmentCUD fragmentCUD = new FragmentCUD();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.FragmentContainer, fragmentCUD);
        fragmentTransaction.commit();

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

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