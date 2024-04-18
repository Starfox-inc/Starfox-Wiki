package integrationAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class matchToSQL {
    public static void insertDataIntoDB(List<Match> matches, String tableName) {
        String insertQuery = "INSERT IGNORE INTO " + tableName + " (begin_at, detailed_stats, draw, end_at, forfeit, games, id, league, league_id, live, " +
                "match_type, name, number_of_games, serie, serie_id, streams_list, tournament, tournament_id, videogame, game_advantage) VALUES (STR_TO_DATE(?, '%Y-%m-%dT%H:%i:%sZ'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/starfox", "starfoxUser", "$tarfox123");
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            for (Match match : matches) {

                // Set parameters for the prepared statement
                insertStmt.setString(1, match.getBegin_at());
                insertStmt.setBoolean(2, match.isDetailed_stats());
                insertStmt.setBoolean(3, match.isDraw());
                insertStmt.setString(4, match.getEnd_at() != null && !match.getEnd_at().isEmpty() ? match.getEnd_at() : null);
                insertStmt.setBoolean(5, match.isForfeit());
                String serializedGame = Serializer.serializeList(match.getGames());
                insertStmt.setObject(6, serializedGame);
                insertStmt.setInt(7, match.getId());
                String serializedLeague = Serializer.serializeObject(match.getLeague());
                insertStmt.setObject(8, serializedLeague);
                insertStmt.setInt(9, match.getLeagueID());
                String serializedLive = Serializer.serializeObject(match.getLivedata());
                insertStmt.setObject(10, serializedLive); // Assuming livedata is an object
                insertStmt.setString(11, match.getMatchType());
                insertStmt.setString(12, match.getName());
                insertStmt.setInt(13, match.getNumberOfGames());
                String serializedSerie = Serializer.serializeObject(match.getSerie());
                insertStmt.setObject(14, serializedSerie); // Assuming serie is an object
                insertStmt.setInt(15, match.getSerieID());
                String serializedStreams = Serializer.serializeList(match.getStreamList());
                insertStmt.setObject(16, serializedStreams); // Assuming streamList is an object
                String serializedtourny = Serializer.serializeObject(match.getTournament());
                insertStmt.setObject(17, serializedtourny); // Assuming tournament is an object
                insertStmt.setInt(18, match.getTournament_id());
                String serializedCurrentGame = Serializer.serializeObject(match.getVideogame());
                insertStmt.setObject(19, serializedCurrentGame); // Assuming videogame is an object
                insertStmt.setInt(20, match.getGame_advantage());
                // Execute the update
                insertStmt.executeUpdate();
            }

            System.out.println("Data inserted successfully into table: " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}