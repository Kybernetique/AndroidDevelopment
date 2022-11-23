package com.example.laba4.database;

public class Worker {
    public int id;
    public String name;
    public int age;

    public Worker() {
    }

    public Worker(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Worker {" + "Name='" + name + '\'' + ", Age=" + age + '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
}
