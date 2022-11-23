package com.example.mobilelabs.storage;

public class Boss {
    public int id;
    public String name;
    public Boss(String name){
        this.name = name;
    }
    public Boss(){
        name = "";
    }
    @Override
    public String toString(){
        return "id = " + id + " name = " + name;
    }
}
