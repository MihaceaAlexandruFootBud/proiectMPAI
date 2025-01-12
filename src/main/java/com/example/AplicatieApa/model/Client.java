package com.example.AplicatieApa.model;

import com.example.AplicatieApa.observer.Observer;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comanda> comenzi;

    public Client() {
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Client(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Client " + name + " notificat: " + message);
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}