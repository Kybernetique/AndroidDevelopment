package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    Button buttonBack;
    ListView listViewResult;
    ArrayList<String> listResult = FragmentCRUD.output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonBack = findViewById(R.id.buttonBack);
        listViewResult = findViewById(R.id.listViewResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ResultActivity.this,
                R.layout.forlist, listResult); // то, как данные будут храниться
        listViewResult.setAdapter(adapter);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}