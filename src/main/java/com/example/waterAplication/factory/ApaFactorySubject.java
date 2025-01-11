package com.example.waterAplication.factory;

import com.example.waterAplication.Singleton.DatabaseConnection;
import com.example.waterAplication.model.Apa;
import com.example.waterAplication.model.ApaPlata;
import com.example.waterAplication.observer.Observer;
import com.example.waterAplication.observer.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApaFactorySubject implements ApaFactory, Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public Apa createApa() {

        Apa apa = new ApaPlata();

        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO apa (descriere) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, apa.getDescriere());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apa;
    }
}
