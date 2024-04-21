package integrationAPI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateRunningMatches {
    private static final String URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String USER = "starfoxUser";
    private static final String PASSWORD = "$tarfox123";
    public static void main(String [] args) throws IOException, InterruptedException{
        dropRunningMatchTable();
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()){
            Test.main(null);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    
    public static void dropRunningMatchTable() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE runningmatchlist");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
