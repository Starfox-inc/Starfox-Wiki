package integrationAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.Starfox.EsportsWiki.model.User;
import com.Starfox.EsportsWiki.repository.UserDAO;
import com.Starfox.EsportsWiki.service.AuthenticationService;

public class userToSQL {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String DB_USERNAME = "starfoxUser";
    private static final String DB_PASSWORD = "$tarfox123";

    public static boolean userExists(String username, String email) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

            checkStmt.setString(1, username);
            checkStmt.setString(2, email);
            
            ResultSet resultSet = checkStmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }

        return false;
    }

    public static boolean insertUserIntoDB(User user) throws SQLException {
        if (userExists(user.getUsername(), user.getEmail())) {
            System.out.println("A user with this username or email already exists.");
            return false;
        }

        String insertQuery = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            insertStmt.setString(1, user.getUsername());
            insertStmt.setString(2, user.getEmail());
            insertStmt.setString(3, user.getPasswordHash());

            int rowsAffected = insertStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User inserted successfully!");
                return true;
            }
        }

        return false;
    }
    //For testing purposes only keep this commented out unless testing
    /* 
    public static void main(String[] args) {
        UserDAO userDao = new UserDAO(); // Make sure this UserDAO is using Spring's dependency injection in production
        AuthenticationService authService = new AuthenticationService(userDao); // This should be autowired in production

        //Hash the password
        String hashedPassword = authService.hashPassword("securePassword123");

        User newUser = new User("testUsername", "testEmail@example.com", hashedPassword);
        try {
            boolean isInserted = insertUserIntoDB(newUser);
            if (isInserted) {
                System.out.println("User inserted successfully!");
            } else {
                //print a message to the console.
                System.out.println("User insertion failed. The user might already exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); //debugging
        }
    }*/
}