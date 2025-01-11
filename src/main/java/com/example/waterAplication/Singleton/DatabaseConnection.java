package com.example.waterAplication.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    // Configurări pentru baza de date
    private final String url = "jdbc:h2:mem:testdb"; // Adresa bazei de date H2
    private final String username = "sa";
    private final String password = "";

    // Constructor privat
    private DatabaseConnection() {
        try {
            // Creăm conexiunea
            this.connection = DriverManager.getConnection(url, username, password);

            // Creăm tabela dacă nu există
            createTableIfNotExists();
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut conecta la baza de date!", e);
        }
    }


    // Metodă publică pentru a obține instanța unică
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Metodă pentru a obține conexiunea
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut reconecta la baza de date!", e);
        }
        return connection;
    }

    // Metodă pentru a crea tabela dacă nu există
    private void createTableIfNotExists() {
        String createTableSQL = """
        CREATE TABLE IF NOT EXISTS apa (
            id INT AUTO_INCREMENT PRIMARY KEY,
            descriere VARCHAR(255)
        );
    """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Tabela `apa` a fost verificată/creată.");
        } catch (SQLException e) {
            System.err.println("Eroare la crearea/verificarea tabelei `apa`:");
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Prevenim reutilizarea unei conexiuni închise
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}