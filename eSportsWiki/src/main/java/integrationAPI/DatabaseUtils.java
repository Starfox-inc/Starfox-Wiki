package integrationAPI;

import java.sql.*;

public class DatabaseUtils {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String USERNAME = "starfoxUser";
    private static final String PASSWORD = "$tarfox123";

    public static void displayTable(String tableName) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sqlQuery = "SELECT * FROM " + tableName;
            try (Statement statement = conn.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    int[] columnWidths = new int[columnCount];
                    int totalWidth = 0;

                    // Calculate the maximum width of each column
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        columnWidths[i - 1] = Math.max(columnName.length(), 20); // Adjust minimum width as needed
                        totalWidth += columnWidths[i - 1];
                    }

                    // Calculate total width of the column names and spaces
                    int totalSpaces = (columnCount - 1) * 2;
                    totalWidth += totalSpaces;

                    // Display column headers
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.printf("%-" + columnWidths[i - 1] + "s  ", metaData.getColumnName(i));
                    }
                    System.out.println();

                    // Display data rows
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            String value = resultSet.getString(i);
                            if (value != null) {
                                // Special handling for the "slug" column to truncate
                                if (metaData.getColumnName(i).equalsIgnoreCase("slug")) {
                                    if (value.length() > 12) {
                                        value = value.substring(0, 10) + "...";
                                    }
                                }
                                // Special handling for the "image_url" column to truncate
                                if (metaData.getColumnName(i).equalsIgnoreCase("image_url")) {
                                    if (value.length() > 20) {
                                        value = value.substring(0, 17) + "...";
                                    }
                                }
                            } else {
                                value = "N/A"; // Placeholder for null values
                            }
                            System.out.printf("%-" + columnWidths[i - 1] + "s  ", value);
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
