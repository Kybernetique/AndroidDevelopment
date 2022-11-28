package com.example.laba4.database.service;

import com.example.laba4.MainActivity;
import com.example.laba4.database.Worker;

import java.util.ArrayList;
import java.util.List;

public interface IService {

    void create(Worker worker);

    void update(Worker model);

    List<Worker> getList();
    void updateWidget();

    void delete(Worker worker);

    int getCount();
}
