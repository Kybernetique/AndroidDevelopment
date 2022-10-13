package com.example.lab1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentCreate extends Fragment {
    ArrayList<String> listOfBosses;
    ListView listViewOfBosses;
    EditText editTextBossFullName;
    Button buttonAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        listOfBosses = new ArrayList<>();
        listViewOfBosses = view.findViewById(R.id.listViewOfBosses); // получили доступ к listview
        editTextBossFullName = view.findViewById(R.id.editTextBossFullName);
        buttonAdd = view.findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!listOfBosses.contains(editTextBossFullName.getText().toString())) {
                        listOfBosses.add(editTextBossFullName.getText().toString());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                R.layout.forlist, listOfBosses); // то, как данные будут храниться
                        listViewOfBosses.setAdapter(adapter);
                        listViewOfBosses.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    } else {
                        Toast.makeText(getActivity(), "The element already exists!",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "Error!",
                            Toast.LENGTH_LONG).show();
                }            }
        });
        return view;
    }

/*    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextBossFullName = view.findViewById(R.id.editTextBossFullName);
        listViewOfBosses = view.findViewById(R.id.listViewOfBosses);
        buttonAdd = view.findViewById(R.id.buttonAdd);
    }*/

/*    protected void addBoss() {
        try {
            if (!listOfBosses.contains(editTextBossFullName.getText().toString())) {
                listOfBosses.add(editTextBossFullName.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                        R.layout.forlist, listOfBosses); // то, как данные будут храниться
                listViewOfBosses.setAdapter(adapter);
                listViewOfBosses.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            } else {
                Toast.makeText(getActivity(), "The element already exists!",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(getActivity(), "Error!",
                    Toast.LENGTH_LONG).show();
        }
    }*/
}