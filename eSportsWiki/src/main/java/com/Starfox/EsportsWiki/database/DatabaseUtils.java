package com.Starfox.EsportsWiki.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String USERNAME = "starfoxUser";
    private static final String PASSWORD = "$tarfox123";

    public static void displayTable(String tableName) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Define the SQL query to retrieve data
            String sqlQuery = "SELECT * FROM " + tableName;

            // Create a statement
            try (Statement statement = conn.createStatement()) {
                // Execute the query
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    int[] columnWidths = new int[columnCount];
                    int totalWidth = 0;

                    // Calculate the maximum width of each column
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        columnWidths[i - 1] = Math.max(columnName.length(), 20); // Adjust the minimum width as needed
                        totalWidth += columnWidths[i - 1];
                    }

                    // Calculate the total width of the column names and spaces
                    int totalSpaces = (columnCount - 1) * 2; // Number of spaces between column names
                    totalWidth += totalSpaces;

                    // Display column headers
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        System.out.printf("%-" + columnWidths[i - 1] + "s", columnName);
                        if (i < columnCount) {
                            // Add spaces between column names
                            for (int j = 0; j < 2; j++) {
                                System.out.print(" ");
                            }
                        }
                    }
                    System.out.println();

                    // Display data rows
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            // Special handling for URL column
                            if (metaData.getColumnName(i).equalsIgnoreCase("image_url")) {
                                String url = resultSet.getString(i);
                                if (url != null) {
                                    if (url.length() > 20) { // Adjust the length as needed
                                        url = url.substring(0, 17) + "..."; // Truncate URL
                                    }
                                    System.out.printf("%-" + columnWidths[i - 1] + "s", url);
                                } else {
                                    System.out.printf("%-" + columnWidths[i - 1] + "s", "N/A"); // Placeholder for null URLs
                                }
                            } else {
                                System.out.printf("%-" + columnWidths[i - 1] + "s", resultSet.getString(i));
                            }
                            if (i < columnCount) {
                                // Add spaces between column values
                                for (int j = 0; j < 2; j++) {
                                    System.out.print(" ");
                                }
                            }
                        }
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//    public static void main(String[] args) {
//        displayTable("playerList"); // Replace "playerList" with the desired table name
//    }
