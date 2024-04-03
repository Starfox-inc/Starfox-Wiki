import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class playerToSQL {

    public static void insertDataIntoDB(List<Player> players) throws SQLException {
        String insertQuery = "INSERT INTO playerlist (active, age, first_name, last_name, name, nationality, role, slug, image_url, teamInfo_id, currentGame_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/starfox", "starfoxUser", "$tarfox123");
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            for (Player player : players) {
                insertStmt.setBoolean(1, player.isActive());
                insertStmt.setInt(2, player.getAge());
                insertStmt.setString(3, player.getFirst_name());
                insertStmt.setString(4, player.getLast_name());
                insertStmt.setString(5, player.getName());
                insertStmt.setString(6, player.getNationality());
                insertStmt.setString(7, player.getRole());
                insertStmt.setString(8, player.getSlug());
                insertStmt.setString(9, player.getImage_url());
                insertStmt.setInt(10, player.getTeamInfo().getId());
                insertStmt.setInt(11, player.getCurrentGame().getId());

                insertStmt.executeUpdate();
            }

            System.out.println("Data inserted successfully!");
        }
    }
}