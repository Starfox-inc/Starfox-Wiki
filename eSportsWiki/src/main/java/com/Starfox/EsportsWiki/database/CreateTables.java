package com.Starfox.EsportsWiki.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CreateTables {
    //TO BE CHANGED BY USER
    private static final String URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String USER = "starfoxUser";
    private static final String PASSWORD = "$tarfox123";

    public static void createTable(String tableName, List<String> columns) {
//        USE CASE :
//        List<String> columns = List.of("id INT AUTO_INCREMENT PRIMARY KEY",
//                "name VARCHAR(255) NOT NULL",
//                "email VARCHAR(255)",
//                "country VARCHAR(255)",
//                "UNIQUE KEY unique_email (email)");
//        createTables.createTable(tableName, columns);
//        Concatenate column definitions into SQL string
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
        for (int i = 0; i < columns.size(); i++) {
            sb.append(columns.get(i));
            if (i < columns.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            // Create a new table
            stmt.execute(sb.toString());
            System.out.println("Table created: " + tableName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}