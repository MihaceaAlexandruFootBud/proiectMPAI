package com.example.AplicatieApa.factory;

import com.example.AplicatieApa.model.Apa;
import com.example.AplicatieApa.model.ApaPlata;

public class ApaPlataFactory implements ApaFactory {

    @Override
    public Apa createApa() {
        return new ApaPlata();
    }
}
