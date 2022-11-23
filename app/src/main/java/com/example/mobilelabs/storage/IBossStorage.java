package com.example.mobilelabs.storage;

import java.util.List;

public interface IBossStorage {
    public List<Boss> getList();
    public void add(Boss Boss);
    public void update(Boss Boss);
    public void delete(Boss Boss);
    public List<Boss> findSimilar(Boss Boss);
}
