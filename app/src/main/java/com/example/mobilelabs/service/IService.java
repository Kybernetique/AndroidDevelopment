package com.example.mobilelabs.service;

import com.example.mobilelabs.storage.Boss;

import java.util.List;

public interface IService {
    public void create(Boss boss);

    public void update(Boss boss);

    public List<Boss> getList();

    public void delete(Boss boss);

}
