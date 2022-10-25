package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentCRUD fragmentCRUD;
    private static final String FRAGMENT_TAG = "fragmentCRUD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragmentCRUD = new FragmentCRUD();
        } else {
            fragmentCRUD = (FragmentCRUD) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentContainer, fragmentCRUD, FRAGMENT_TAG)
                .commit();
    }

/*    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        fragmentCRUD.listOfBosses = savedInstanceState.getStringArrayList(key);
        super.onRestoreInstanceState(savedInstanceState);
    }*/
}