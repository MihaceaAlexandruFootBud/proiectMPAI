package com.example.AplicatieApa.factory;

import com.example.AplicatieApa.model.Apa;
import com.example.AplicatieApa.model.ApaMinerala;

public class ApaMineralaFactory implements ApaFactory {


    @Override
    public Apa createApa() {
        return new ApaMinerala();
    }

}
