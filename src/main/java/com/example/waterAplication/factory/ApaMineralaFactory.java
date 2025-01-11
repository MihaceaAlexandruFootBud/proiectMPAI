package com.example.waterAplication.factory;

import com.example.waterAplication.model.Apa;
import com.example.waterAplication.model.ApaMinerala;

public class ApaMineralaFactory implements ApaFactory{
    @Override
    public Apa createApa() {
        return new ApaMinerala();
    }
}
