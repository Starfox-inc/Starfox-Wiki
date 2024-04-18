import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class playerToSQL {
    public static void insertDataIntoDB(List<Player> players, String gameName) throws SQLException {
        String insertQuery = "INSERT INTO " + gameName + " (playerId, active, age, first_name, last_name, name, nationality, role, slug, image_url, teaminfo_teamID, current_videogame_gameID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/starfox", "starfoxUser", "$tarfox123");
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            for (Player player : players) {
                // Set player ID
                insertStmt.setInt(1, player.getPlayerId());
                insertStmt.setBoolean(2, player.isActive());
                insertStmt.setInt(3, player.getAge());
                insertStmt.setString(4, player.getFirst_name());
                insertStmt.setString(5, player.getLast_name());
                insertStmt.setString(6, player.getName());
                insertStmt.setString(7, player.getNationality());
                insertStmt.setString(8, player.getRole());
                insertStmt.setString(9, player.getSlug());
                insertStmt.setString(10, player.getImage_url());
                if (player.getTeaminfo() != null) {
                    insertStmt.setInt(11, player.getTeaminfo().getId());
                } else {
                    // Handle the case where teaminfo is null (e.g., set a default value or use NULL)
                    insertStmt.setNull(11, java.sql.Types.INTEGER);
                }

                if (player.getCurrentVideogame() != null) {
                    insertStmt.setInt(12, player.getCurrentVideogame().getId());
                } else {
                    // Handle the case where currentVideogame is null (e.g., set a default value or use NULL)
                    insertStmt.setNull(12, java.sql.Types.INTEGER);
                }


                insertStmt.executeUpdate();
            }

            System.out.println("Data inserted successfully!");
        }
    }
}