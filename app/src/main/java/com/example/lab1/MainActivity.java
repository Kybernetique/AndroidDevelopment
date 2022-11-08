package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BossFragment bossFragment;
    static final String BOSS_FRAGMENT_TAG = "bossFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            bossFragment = new BossFragment();
        } else {
            bossFragment = (BossFragment) getSupportFragmentManager().findFragmentByTag(BOSS_FRAGMENT_TAG);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentContainer, bossFragment, BOSS_FRAGMENT_TAG)
                .commit();
    }
}