package integrationAPI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.JSONArray;

public class UpdateDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String USER = "starfoxUser";
    private static final String PASSWORD = "$tarfox123";
    public static void main(String [] args) throws IOException, InterruptedException{
    }



    public static void updateTable(String tablename, String APIurl) throws IOException, InterruptedException, URISyntaxException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE " + tablename);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        List<String> runningMatchesColumns = List.of(
            "begin_at TIMESTAMP",
            "detailed_stats BOOLEAN",
            "draw BOOLEAN",
            "end_at TIMESTAMP",
            "forfeit BOOLEAN",
            "games VARCHAR(3000)", // Assuming games will be stored as a JSON string
            "id INT PRIMARY KEY",
            "league VARCHAR(255)", // Assuming league will be stored as a JSON string
            "league_id INT",
            "live VARCHAR(255)", // Assuming livedata will be stored as a JSON string
            "match_type VARCHAR(255)",
            "name VARCHAR(255)",
            "number_of_games INT",
            "serie VARCHAR(255)", // Assuming serie will be stored as a JSON string
            "serie_id INT",
            "streams_list VARCHAR(2000)", // Assuming streamList will be stored as a JSON string
            "tournament VARCHAR(255)", // Assuming tournament will be stored as a JSON string
            "tournament_id INT",
            "videogame VARCHAR(255)", // Assuming videogame will be stored as a JSON string
            "game_advantage INT"
    );

        integrationAPI.CreateTables.createTable(tablename, runningMatchesColumns);

        JSONArray runningMatches = Test.fetchDataFromAPI(APIurl);

        List<Match> runningMatchList = Match.parseMatch(runningMatches);

        matchToSQL.insertDataIntoDB(runningMatchList, tablename);

        
        
    }
}
