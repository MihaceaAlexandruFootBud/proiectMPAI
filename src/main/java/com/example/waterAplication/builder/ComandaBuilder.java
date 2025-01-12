package com.example.waterAplication.builder;

import com.example.waterAplication.factory.ApaMineralaFactory;
import com.example.waterAplication.factory.ApaPlataFactory;
import com.example.waterAplication.model.Apa;
import com.example.waterAplication.model.Client;
import com.example.waterAplication.model.Comanda;
import com.example.waterAplication.observer.NotificationManager;
import com.example.waterAplication.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ComandaBuilder implements IBuilder {
    private String tipApa;
    private int cantitate;
    private String adresaLivrare;
    private boolean includeDozator;
    private String dataLivrare;
    private Client client;
    private final NotificationManager notificationManager;
    private final ApaMineralaFactory apaMineralaFactory = new ApaMineralaFactory();
    private final ApaPlataFactory apaPlataFactory = new ApaPlataFactory();

    public ComandaBuilder(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public ComandaBuilder setClient(Client client) {
        this.client = client;
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
        if (tipApa == null || tipApa.isEmpty()) {
            throw new IllegalStateException("Tipul de apă trebuie să fie specificat!");
        }

        List<Apa> apaList = new ArrayList<>();
        for (int i = 0; i < cantitate; i++) {
            if ("Minerală".equalsIgnoreCase(tipApa)) {
                apaList.add(apaMineralaFactory.createApa());
            } else if ("Plată".equalsIgnoreCase(tipApa)) {
                apaList.add(apaPlataFactory.createApa());
            } else {
                throw new IllegalStateException("Tipul de apă nu este valid: " + tipApa);
            }
        }


        Comanda comanda = new Comanda(tipApa, cantitate, adresaLivrare, includeDozator, dataLivrare, apaList, client);


        if (notificationManager.hasObservers()) {
            String message = "Salut, " + this.client.getName() + "!\n\nComanda ta de " + tipApa +
                    " este gata de livrare.\n\nAdresa de livrare: " + adresaLivrare +
                    "\nData livrării: " + dataLivrare + "\n\nVă mulțumim!";
            notificationManager.notifyObservers(message);
        }

        return comanda;
    }
}