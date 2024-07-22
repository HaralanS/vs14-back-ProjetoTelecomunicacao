package br.com.dbc.vemser.projetoTelecomunicacoes.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {
    private static final String URL = "jdbc:postgresql://dpg-cqem3k1u0jms739fa8u0-a.oregon-postgres.render.com/telecomunicacoes_bd_postgres";
    private static final String USER = "telecomunicacoes_bd_postgres_user";
    private static final String PASSWORD = "jYlbO2nBqQHXDG1AI5thZhKrqBp0wFnK";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
