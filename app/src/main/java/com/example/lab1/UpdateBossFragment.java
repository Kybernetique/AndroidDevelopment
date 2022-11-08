package com.example.lab1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class UpdateBossFragment extends Fragment {
    View view;

    ArrayList<String> listOfBosses;
    int selectedItemPosition;

    EditText editTextBossFullName;
    Button buttonOk;
    Button buttonCancel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_update_boss, container, false);

        editTextBossFullName = view.findViewById(R.id.editTextBossFullName);
        buttonOk = view.findViewById(R.id.buttonOk);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        Bundle bundle = getArguments();
        if (bundle != null) {
            listOfBosses = bundle.getStringArrayList(BossFragment.keyList);
            selectedItemPosition = bundle.getInt(BossFragment.keyInt);
        }
        editTextBossFullName.setText(listOfBosses.get(selectedItemPosition));

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BossFragment bossFragment = (BossFragment) getFragmentManager()
                        .findFragmentByTag(MainActivity.BOSS_FRAGMENT_TAG);
                listOfBosses.set(selectedItemPosition, editTextBossFullName.getText().toString());
                bossFragment.adapter.notifyDataSetChanged();
                getFragmentManager().popBackStackImmediate(MainActivity.BOSS_FRAGMENT_TAG, 1);

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .popBackStack(MainActivity.BOSS_FRAGMENT_TAG, 1);
            }
        });

        return view;
    }
}