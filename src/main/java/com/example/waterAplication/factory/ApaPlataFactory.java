package com.example.waterAplication.factory;

import com.example.waterAplication.model.Apa;
import com.example.waterAplication.model.ApaPlata;

public class ApaPlataFactory implements ApaFactory {

    @Override
    public Apa createApa() {
        return new ApaPlata();
    }
}
