package com.example.AplicatieApa;

import com.example.AplicatieApa.singleton.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
			databaseConnection.getConnection();
			System.out.println("Conexiunea la baza de date a fost inițializată cu succes.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnection.getInstance().closeConnection();
				System.out.println("Conexiunea la baza de date a fost închisă.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
