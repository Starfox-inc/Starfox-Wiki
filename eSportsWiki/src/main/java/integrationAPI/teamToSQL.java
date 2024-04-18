package integrationAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class teamToSQL {
    public static void insertDataIntoDB(List<Team> teams, String gameName) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/starfox", "starfoxUser", "$tarfox123")) {
            String insertQuery = "INSERT INTO " + gameName + " (teamID, acronym, current_videogame_id, current_videogame_name, current_videogame_slug, image_url, location, name, slug) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                for (Team team : teams) {
                    insertStmt.setString(2, team.getAcronym());
                    insertStmt.setInt(3, team.getCurrent_videogame().getId());
                    insertStmt.setString(4, team.getCurrent_videogame().getName());
                    insertStmt.setString(5, team.getCurrent_videogame().getSlug());
                    insertStmt.setInt(1, team.getId());
                    insertStmt.setString(6, team.getImage_url());
                    insertStmt.setString(7, team.getLocation());
                    insertStmt.setString(8, team.getName());
                    insertStmt.setString(9, team.getSlug());
                    insertStmt.executeUpdate();
                    }
                }
            }
            System.out.println("Data inserted successfully!");
        }
}