package com.example.AplicatieApa.controller;

import com.example.AplicatieApa.model.Client;
import com.example.AplicatieApa.model.Comanda;
import com.example.AplicatieApa.observer.NotificationManager;
import com.example.AplicatieApa.repository.ClientRepository;
import com.example.AplicatieApa.repository.ComandaRepository;
import com.example.AplicatieApa.builder.ComandaBuilder;

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