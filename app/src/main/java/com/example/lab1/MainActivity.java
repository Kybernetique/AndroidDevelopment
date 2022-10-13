package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
/*    Button buttonSelectAll;
    Button buttonDeselectAll;
    Button buttonPrintAll;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentCRUD fragmentCRUD = new FragmentCRUD();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.FragmentContainer, fragmentCRUD);
        fragmentTransaction.commit();
    }
}