package com.Starfox.EsportsWiki.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class UserPreferencesDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String DB_USERNAME = "starfoxUser";
    private static final String DB_PASSWORD = "$tarfox123";

    public void saveUserPreferences(int userId, boolean liveData, boolean teamData, boolean playerData) {
        String query = "INSERT INTO user_preferences (user_id, live_data, team_data, player_data) VALUES (?, ?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE live_data = ?, team_data = ?, player_data = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setBoolean(2, liveData);
            stmt.setBoolean(3, teamData);
            stmt.setBoolean(4, playerData);
            stmt.setBoolean(5, liveData);
            stmt.setBoolean(6, teamData);
            stmt.setBoolean(7, playerData);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean[] getUserPreferences(int userId) {
        String query = "SELECT live_data, team_data, player_data FROM user_preferences WHERE user_id = ?";
        boolean[] preferences = new boolean[3];
        System.out.println("1");
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                preferences[0] = rs.getBoolean("live_data");
                preferences[1] = rs.getBoolean("team_data");
                preferences[2] = rs.getBoolean("player_data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preferences;
    }
}