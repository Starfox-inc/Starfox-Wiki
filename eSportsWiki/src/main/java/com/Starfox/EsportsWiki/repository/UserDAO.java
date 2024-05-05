package com.Starfox.EsportsWiki.repository;

import com.Starfox.EsportsWiki.model.User;
import java.sql.*;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String DB_USERNAME = "starfoxUser";
    private static final String DB_PASSWORD = "$tarfox123";

    //Save a new user to the database
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPasswordHash());
            
            logger.info("Executing SQL: {}", sql);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                //get the generated user ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setUserId(generatedKeys.getInt(1));
                        logger.info("User added with ID: {}", user.getUserId());
                        return true;
                    }
                }
            }else{
                logger.warn("No rows affected, user not added");
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            logger.error("Error adding user: ", e);
            e.printStackTrace();
        }

        return false;
    }

    //Get a user by username
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash")
                );
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return null;
    }
    
    //updateUser, deleteUser
    //update a user in the database
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, password_hash = ? WHERE user_id = ?";
    
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPasswordHash());
            pstmt.setInt(4, user.getUserId());
    
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //delete a user from the database
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
    
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, userId);
    
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}