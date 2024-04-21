package integrationAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class gameListToSQL {
    private static String url = "jdbc:mysql://localhost:3306/starfox";
    private static String user = "starfoxUser";
    private static String password = "$tarfox123";

    public static void insertDataIntoDB(List<CurrentVideoGame> videoGames) {
        String sql = "INSERT INTO current_videogame (id, name, slug) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement insertStmt = conn.prepareStatement(sql)) {

            for (CurrentVideoGame game : videoGames) {
                insertStmt.setString(2, game.getName());
                insertStmt.setString(3, game.getSlug());
                insertStmt.setInt(1, game.getId());
                insertStmt.addBatch();
            }

            insertStmt.executeBatch();
            System.out.println("Video games inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}