package com.example.waterAplication.controller;

import com.example.waterAplication.model.Client;
import com.example.waterAplication.model.Comanda;
import com.example.waterAplication.observer.NotificationManager;
import com.example.waterAplication.repository.ClientRepository;
import com.example.waterAplication.repository.ComandaRepository;
import com.example.waterAplication.builder.ComandaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComandaController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private NotificationManager notificationManager;

    @PostMapping("/adauga-comanda")
    public String adaugaComanda(
            @RequestParam String nume,
            @RequestParam String email,
            @RequestParam String adresa,
            @RequestParam String tipApa,
            @RequestParam int cantitate,
            @RequestParam(required = false, defaultValue = "false") boolean dozator,
            @RequestParam String dataLivrare) {


        Client client = new Client(nume, email);
        client = clientRepository.save(client);

        notificationManager.addObserver(client);

        Comanda comanda = new ComandaBuilder(notificationManager)
                .setTipApa(tipApa)
                .setCantitate(cantitate)
                .setAdresaLivrare(adresa)
                .setIncludeDozator(dozator)
                .setDataLivrare(dataLivrare)
                .setClient(client)
                .build();

        comandaRepository.save(comanda);

        return "redirect:/succes";
    }
}