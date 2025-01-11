package com.example.waterAplication.builder;

import com.example.waterAplication.model.Comanda;

public interface IBuilder {
    IBuilder setTipApa(String tipApa);
    IBuilder setCantitate(int cantitate);
    IBuilder setAdresaLivrare(String adresa);
    IBuilder setIncludeDozator(boolean includeDozator);
    IBuilder setDataLivrare(String dataLivrare);

    Comanda build();
}