package com.example.AplicatieApa.builder;

import com.example.AplicatieApa.model.Comanda;

public interface IBuilder {
    IBuilder setTipApa(String tipApa);
    IBuilder setCantitate(int cantitate);
    IBuilder setAdresaLivrare(String adresa);
    IBuilder setIncludeDozator(boolean includeDozator);
    IBuilder setDataLivrare(String dataLivrare);

    Comanda build();
}