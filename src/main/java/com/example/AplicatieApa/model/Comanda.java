package com.example.AplicatieApa.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private final String tipApa;
    private final int cantitate;
    private final String adresaLivrare;
    private final boolean includeDozator;
    private final String dataLivrare;
    @Transient
    private final List<Apa> apaList;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private final Client client;

    public Comanda(String tipApa, int cantitate, String adresaLivrare, boolean includeDozator,
                   String dataLivrare, List<Apa> apaList, Client client) {
        this.tipApa = tipApa;
        this.cantitate = cantitate;
        this.adresaLivrare = adresaLivrare;
        this.includeDozator = includeDozator;
        this.dataLivrare = dataLivrare;
        this.apaList = apaList;
        this.client = client;
    }

    public String getTipApa() {
        return tipApa;
    }

    public int getCantitate() {
        return cantitate;
    }

    public String getAdresaLivrare() {
        return adresaLivrare;
    }

    public boolean isIncludeDozator() {
        return includeDozator;
    }

    public String getDataLivrare() {
        return dataLivrare;
    }

    public List<Apa> getApaList() {
        return apaList;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        String apaListString = apaList.stream()
                .map(Apa::toString)
                .collect(Collectors.joining(", "));

        return "Comanda [tipApa=" + tipApa +
                ", cantitate=" + cantitate + "L" +
                ", adresaLivrare=" + adresaLivrare +
                ", includeDozator=" + includeDozator +
                ", dataLivrare=" + dataLivrare +
                ", client=" + client +
                ", apaList=[" + apaListString + "]]";
    }
}