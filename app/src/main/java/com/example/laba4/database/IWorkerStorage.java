package com.example.laba4.database;

import java.util.List;

public interface IWorkerStorage {

    void create(Worker model);

    void update(Worker model);

    Worker getElement(Worker model);

    List<Worker> getFilteredList(Worker model);

    List<Worker> getFullList();

    void delete();

    void delete(Worker model);
}
