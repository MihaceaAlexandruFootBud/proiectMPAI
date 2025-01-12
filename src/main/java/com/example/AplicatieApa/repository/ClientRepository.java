package com.example.AplicatieApa.repository;

import com.example.AplicatieApa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}