package com.example.AplicatieApa.repository;

import com.example.AplicatieApa.model.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComandaRepository extends JpaRepository<Comanda, Integer> {
}