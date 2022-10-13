package com.example.lab1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentCUD extends Fragment {
    ArrayAdapter<String> adapter;

    ArrayList<String> listOfBosses;
    ListView listViewOfBosses;
    EditText editTextBossFullName;
    Button buttonCreate;
    Button buttonUpdate;
    Button buttonDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cud, container, false);

        listOfBosses = new ArrayList<>();
        listViewOfBosses = view.findViewById(R.id.listViewOfBosses); // получили доступ к listview
        editTextBossFullName = view.findViewById(R.id.editTextBossFullName);
        buttonCreate = view.findViewById(R.id.buttonCreate);
        buttonUpdate = view.findViewById(R.id.buttonUpdate);
        buttonDelete = view.findViewById(R.id.buttonDelete);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!listOfBosses.contains(editTextBossFullName.getText().toString())) {
                        listOfBosses.add(editTextBossFullName.getText().toString());
                        adapter = new ArrayAdapter<>(getActivity(),
                                R.layout.forlist, listOfBosses); // то, как данные будут храниться
                        listViewOfBosses.setAdapter(adapter);
                        listViewOfBosses.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    for (int i = 0; i < listOfBosses.size(); i++) {
                        if (listViewOfBosses.isItemChecked(i)) {
                            listOfBosses.remove(i);
                            listViewOfBosses.clearChoices();
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "Error!",
                            Toast.LENGTH_SHORT).show();
                }
                /*                SparseBooleanArray sbArray = listViewOfBosses.getCheckedItemPositions();
                for (int i = 0; i < sbArray.size(); i++) {
                    int key = sbArray.keyAt(i);
                    if ( sbArray.get(key)) {
                        sbArray.delete(key);
                        listOfBosses.remove(key);
                    }
                }*/
            }
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

}