package com.example.waterAplication.model;

import java.util.List;
import java.util.stream.Collectors;

public class Comanda {
    private final String tipApa;
    private final int cantitate;
    private final String adresaLivrare;
    private final boolean includeDozator;
    private final String dataLivrare;
    private final List<Apa> apaList;

    public Comanda(String tipApa, int cantitate, String adresaLivrare, boolean includeDozator, String dataLivrare, List<Apa> apaList) {
        this.tipApa = tipApa;
        this.cantitate = cantitate;
        this.adresaLivrare = adresaLivrare;
        this.includeDozator = includeDozator;
        this.dataLivrare = dataLivrare;
        this.apaList = apaList;
    }

    @Override
    public String toString() {
        String apaListString = apaList.stream()
                .map(Apa::toString)
                .collect(Collectors.joining(", "));

        return "Comanda [tipApa=" + tipApa + ", cantitate=" + cantitate + "L, adresaLivrare=" + adresaLivrare +
                ", includeDozator=" + includeDozator + ", dataLivrare=" + dataLivrare + ", apaList=[" + apaListString + "]]";
    }
}