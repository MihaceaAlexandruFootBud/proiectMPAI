package com.example.AplicatieApa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/comanda")
    public String afiseazaFormularul() {
        return "formular";
    }

    @GetMapping("/succes")
    public  String afiseazaPaginaSuccea(){
        return "succes";
    }
}