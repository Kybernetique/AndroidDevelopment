package com.example.mobilelabs.storage;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.content.Context;

public class FileBossStorage implements IBossStorage {
    private final String fileName = "bossesFile";
    private static ArrayList<Boss> Bosses;
    private Context context;

    public FileBossStorage(Context context){
        this.context = context;
        importFromJSON();
    }

    @Override
    public ArrayList<Boss> getList() {
        return Bosses;
    }

    @Override
    public void add(Boss Boss) {
        int id = 0;
        for (int i = 0; i < Bosses.size(); ++i){
            if (id <= Bosses.get(i).id){
                id = Bosses.get(i).id + 1;
            }
        }
        Boss.id = id;
        Bosses.add(Boss);
        exportToJSON();
    }

    @Override
    public void update(Boss Boss) {
        for (int i = 0; i < Bosses.size(); ++i){
            if (Bosses.get(i).id == Boss.id){
                Bosses.get(i).name = Boss.name;
            }
        }
        exportToJSON();
    }

    @Override
    public void delete(Boss Boss) {
        int index = -1;
        for (int i = 0; i < Bosses.size(); ++i){
            if (Bosses.get(i).id == Boss.id){
                index = i;
            }
        }
        if (index != -1){
            Bosses.remove(index);
        }
        exportToJSON();
    }

    @Override
    public ArrayList<Boss> findSimilar(Boss Boss) {
        ArrayList<Boss> similarBosses = new ArrayList<Boss>();
        for (int i = 0; i < Bosses.size(); ++i){
            if (Bosses.get(i).name.contains(Boss.name)){
                similarBosses.add(Bosses.get(i));
            }
        }
        return similarBosses;
    }
    private void exportToJSON() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                DataItems dataItems = new DataItems();
                dataItems.setBosses(Bosses);
                String jsonString = gson.toJson(dataItems);

                try(FileOutputStream fileOutputStream =
                            context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
                    fileOutputStream.write(jsonString.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        while(thread.isAlive()){}
    }
    private void importFromJSON() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try(FileInputStream fileInputStream = context.openFileInput(fileName);
                    InputStreamReader streamReader = new InputStreamReader(fileInputStream)){
                    Gson gson = new Gson();
                    DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
                    Bosses = (ArrayList<Boss>) dataItems.getBosses();
                }
                catch (IOException ex){
                    Bosses = new ArrayList<Boss>();
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
        while(thread.isAlive()){}
    }
    private static class DataItems {
        private List<Boss> Bosses;

        List<Boss> getBosses() {
            return Bosses;
        }
        void setBosses(List<Boss> Bosses) {
            this.Bosses = Bosses;
        }
    }
}
