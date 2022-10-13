package com.example.lab1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FragmentCRUD extends Fragment {
    ArrayAdapter<String> adapter;

    ArrayList<String> listOfBosses;
    ListView listViewOfBosses;

    EditText editTextBossFullName;

    Button buttonCreate;
    Button buttonRead;
    Button buttonUpdate;
    Button buttonDelete;

    int selectedItemPosition;
    String selectedItemValue;

    static ArrayList<String> output;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crud, container, false);

        listOfBosses = new ArrayList<>();
        listViewOfBosses = view.findViewById(R.id.listViewOfBosses); // получили доступ к listview
        editTextBossFullName = view.findViewById(R.id.editTextBossFullName);

        buttonCreate = view.findViewById(R.id.buttonCreate);
        buttonRead = view.findViewById(R.id.buttonRead);
        buttonUpdate = view.findViewById(R.id.buttonUpdate);
        buttonDelete = view.findViewById(R.id.buttonDelete);


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

                        adapter = new ArrayAdapter<>(getActivity(),
                                R.layout.forlist, listOfBosses); // то, как данные будут храниться
                        listViewOfBosses.setAdapter(adapter);
                        listViewOfBosses.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                        listViewOfBosses.clearChoices();
                    } else {
                        Toast.makeText(getActivity(), "The element already exists!",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "Error!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editTextBossFullName.getText().toString();
                output = (ArrayList<String>) listOfBosses
                        .stream()
                        .filter(value -> value.startsWith(input))
                        .collect(Collectors.toList());
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
                            listOfBosses.set(selectedItemPosition, editTextBossFullName
                                    .getText().toString());
                            adapter.notifyDataSetChanged();
                            listViewOfBosses.clearChoices();
                        }
                        else {
                            Toast.makeText(getActivity(), "There is already an element with this name.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "Select an item first.",
                            Toast.LENGTH_LONG).show();
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
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "Error!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}