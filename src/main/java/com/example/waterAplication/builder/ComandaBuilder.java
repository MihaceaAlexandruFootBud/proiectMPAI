package com.example.waterAplication.builder;

import com.example.waterAplication.factory.ApaFactorySubject;
import com.example.waterAplication.model.Apa;
import com.example.waterAplication.model.Comanda;
import com.example.waterAplication.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ComandaBuilder implements IBuilder{
    private String tipApa;
    private int cantitate;
    private String adresaLivrare;
    private boolean includeDozator;
    private String dataLivrare;
    private ApaFactorySubject apaFactorySubject;
    private List<Observer> observers = new ArrayList<>();

    public ComandaBuilder setApaFactorySubject(ApaFactorySubject apaFactorySubject) {
        this.apaFactorySubject = apaFactorySubject;
        return this;
    }

    public ComandaBuilder addObserver(Observer observer) {
        this.observers.add(observer);
        return this;
    }

    @Override
    public ComandaBuilder setTipApa(String tipApa) {
        this.tipApa = tipApa;
        return this;
    }

    @Override
    public ComandaBuilder setCantitate(int cantitate) {
        this.cantitate = cantitate;
        return this;
    }

    @Override
    public ComandaBuilder setAdresaLivrare(String adresa) {
        this.adresaLivrare = adresa;
        return this;
    }

    @Override
    public ComandaBuilder setIncludeDozator(boolean includeDozator) {
        this.includeDozator = includeDozator;
        return this;
    }

    @Override
    public ComandaBuilder setDataLivrare(String dataLivrare) {
        this.dataLivrare = dataLivrare;
        return this;
    }

    @Override
    public Comanda build() {
        if (apaFactorySubject == null) {
            throw new IllegalStateException("ApaFactorySubject nu este setată!");
        }

        // Adăugăm observatorii la subiect
        for (Observer observer : observers) {
            apaFactorySubject.addObserver(observer);
        }

        // Generăm lista de ape
        List<Apa> apaList = new ArrayList<>();
        for (int i = 0; i < cantitate; i++) {
            apaList.add(apaFactorySubject.createApa());
        }

        return new Comanda(tipApa, cantitate, adresaLivrare, includeDozator, dataLivrare, apaList);
    }
}