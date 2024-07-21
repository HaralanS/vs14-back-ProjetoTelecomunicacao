package br.com.dbc.vemser.pessoaapi.conectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresInsert {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://5432:5432/db-postgres";
        String user = "postgres";
        String password = "12345";

        String insertSQL = "INSERT INTO pessoas (nome, idade) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, "João");
            pstmt.setInt(2, 30);
            pstmt.executeUpdate();

            pstmt.setString(1, "Maria");
            pstmt.setInt(2, 25);
            pstmt.executeUpdate();

            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados.");
            e.printStackTrace();
        }
    }
}
