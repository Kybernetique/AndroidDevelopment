package com.example.lab1;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FragmentCRUD extends Fragment {
    FrameLayout frameLayout;

    View view;

    ArrayList<String> listOfBosses = new ArrayList<>();
    ArrayAdapter adapter; // то, как данные будут храниться

    int selectedItemPosition;
    String selectedItemValue;

    static ArrayList<String> output;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frameLayout = new FrameLayout(getActivity());
        inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.fragment_crud, container, false);

        ListView listViewOfBosses = view.findViewById(R.id.listViewOfBosses); // получили доступ к listview
        EditText editTextBossFullName = view.findViewById(R.id.editTextBossFullName);

        Button buttonCreate = view.findViewById(R.id.buttonCreate);
        Button buttonRead = view.findViewById(R.id.buttonRead);
        Button buttonUpdate = view.findViewById(R.id.buttonUpdate);
        Button buttonDelete = view.findViewById(R.id.buttonDelete);

        listViewOfBosses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemPosition = i;
                selectedItemValue = listViewOfBosses.getItemAtPosition(i).toString();
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!listOfBosses.contains(editTextBossFullName.getText().toString())) {
                        listOfBosses.add(editTextBossFullName.getText().toString());
                        adapter = new ArrayAdapter<>(getActivity(), R.layout.forlist, listOfBosses);
                        listViewOfBosses.setAdapter(adapter);
                        listViewOfBosses.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        listViewOfBosses.clearChoices();
                    }
                    else {
                        Toast.makeText(getActivity(), "The element already exists!", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception ex) {
                    Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editTextBossFullName.getText().toString();
                output = (ArrayList<String>) listOfBosses.stream().filter(value -> value.startsWith(input)).collect(Collectors.toList());
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                (getActivity()).startActivity(intent);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!selectedItemValue.isEmpty()) {
                        if (!listOfBosses.contains(editTextBossFullName.getText().toString())) {
                            listOfBosses.set(selectedItemPosition, editTextBossFullName.getText().toString());
                            adapter.notifyDataSetChanged();
                            listViewOfBosses.clearChoices();
                        }
                        else {
                            Toast.makeText(getActivity(), "There is already an element with this name.", Toast.LENGTH_LONG).show();
                        }
                    }

                }
                catch (Exception ex) {
                    Toast.makeText(getActivity(), "Select an item first.", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    listOfBosses.remove(selectedItemPosition);
                    adapter.notifyDataSetChanged();
                    listViewOfBosses.clearChoices();
                }
                catch (Exception ex) {
                    Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        frameLayout.addView(view);

        return frameLayout;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        frameLayout.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.fragment_crud, null);

        frameLayout.addView(view);
    }

}