package com.example.waterAplication.model;

import com.example.waterAplication.observer.Observer;

public class Client implements Observer {
    private int id; // ID-ul clientului pentru integrarea cu baza de date
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Client " + name + " notificat: " + message);
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + "]";
    }
}