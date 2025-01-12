package com.example.waterAplication.observer;

import com.example.waterAplication.model.Client;
import com.example.waterAplication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationManager implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Autowired
    private EmailService emailService;

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
        List<Observer> notifiedObservers = new ArrayList<>(observers);
        for (Observer observer : notifiedObservers) {
            observer.update(message);

            if (observer instanceof Client client) {
                emailService.sendEmail(client.getEmail(), "Notificare comandă", message);
            }

            removeObserver(observer);
        }
    }

    public boolean hasObservers() {
        return !observers.isEmpty();
    }
}