package com.example.waterAplication;

import com.example.waterAplication.Singleton.DatabaseConnection;
import com.example.waterAplication.factory.ApaFactorySubject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

//		ApaFactorySubject factory = new ApaFactorySubject();
//
//		Client client1 = new Client("Ion");
//		Client client2 = new Client("Maria");
//
//		// Builder pentru comenzi
//		ComandaBuilder builder = new ComandaBuilder();
//		builder.setApaFactorySubject(factory)
//				.addObserver(client1)
//				.addObserver(client2)
//				.setTipApa("Apă plată")
//				.setCantitate(3)
//				.setAdresaLivrare("Strada Exemplu, Nr. 10")
//				.setIncludeDozator(false)
//				.setDataLivrare("2025-01-15");
//
//		// Construim comanda
//		Comanda comanda = builder.build();
//		System.out.println(comanda);

		// Factory pentru apă
		ApaFactorySubject factory = new ApaFactorySubject();

		// Creăm câteva ape
		factory.createApa();
		factory.createApa();

		// Verificăm ce s-a salvat în baza de date
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			String sql = "SELECT * FROM apa";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("id") + ", Descriere: " + resultSet.getString("descriere"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Închidem conexiunea la final
		DatabaseConnection.getInstance().closeConnection();

	}

}
