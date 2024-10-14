package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConexaoDB {

    Connection conn = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/funcionarios"; // Altere para o seu URL
    private static final String USER = "root"; // Altere para o seu usuário
    private static final String PASSWORD = "250509"; // Altere para a sua senha

    public Connection getConnection() {
        try {
            // Registrar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer a conexão
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM funcionarios";
            ResultSet resultSet = statement.executeQuery(query);

            // Processa os resultados
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nome: " + resultSet.getString("nome"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return conn;

    }

}
