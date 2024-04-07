import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.List;
public class Test {
    //please name your database starfox and create a new user with the username and password to maintain consistency across code
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String USERNAME = "starfoxUser";
    private static final String PASSWORD = "$tarfox123";
    private static final String bearerToken = "UzzOazUKLU9n3-7TaaT6SRtiU7lPjDVJ-B1rx76kO8hzvam17o8";
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

//        NOT TO BE COMMENTED OUT -> USE THIS IN MYSQL TO GIVE KEY CONSTRAINTS FOR TABLES
//        ALTER TABLE playerlist
//        ADD CONSTRAINT fk_teaminfo_teamID
//        FOREIGN KEY (teaminfo_teamID) REFERENCES teaminfo(teamID);
//
//        ALTER TABLE playerlist
//        ADD CONSTRAINT fk_current_videogame_gameID
//        FOREIGN KEY (current_videogame_gameID) REFERENCES current_videogame(gameID);
//        END COMMENT -> BELOW CAN BE COMMENTED BACK INTO CODE



//         // CREATE COLUMN LISTS FOR EACH TABLE
//        List<String> current_videogameColumns= List.of("id INT AUTO_INCREMENT PRIMARY KEY",
                                                //        "name VARCHAR(255)",
                                                //        "slug VARCHAR(255)"
                                                //        );

//        List<String> playerListColumns = List.of(
//                "playerId INT NOT NULL PRIMARY KEY",
//                "active TINYINT(1)",
//                "age INT",
//                "first_name VARCHAR(255)",
//                "last_name VARCHAR(255)",
//                "name VARCHAR(255)",
//                "nationality VARCHAR(255)",
//                "role VARCHAR(255)",
//                "slug VARCHAR(255)",
//                "image_url VARCHAR(255)",
//                "teaminfo_teamID INT",
//                "current_videogame_gameID INT"
//        );

//        List<String> teamInfoColumns = List.of(
//                "teamID INT NOT NULL PRIMARY KEY",
//                "acronym VARCHAR(255)",
//                "current_videogame_id INT",
//                "current_videogame_name VARCHAR(255)",
//                "current_videogame_slug VARCHAR(255)",
//                "image_url VARCHAR(255)",
//                "name VARCHAR(255)",
//                "location VARCHAR(255)",
//                "slug VARCHAR(255)"
//        );

//         // CREATE TABLES USING TABLE DEFINITIONS FROM ABOVE
//        CreateTables.createTable("playerlist", playerListColumns);
//        CreateTables.createTable("current_videogame", current_videogameColumns);
//        CreateTables.createTable("teaminfo", teaminfoColumns);
//        CreateTables.createTable("codteams", teaminfoColumns);
//        CreateTables.createTable("csteams", teaminfoColumns);
//        CreateTables.createTable("lolteams", teaminfoColumns);
//        CreateTables.createTable("valteams", teaminfoColumns);

//        Filling tables - starting with teaminfo table
//        Call to fetchDataFromAPI to get the JSONArray
//        JSONArray jsonArray = fetchDataFromAPI("https://api.pandascore.co/teams");
//
//
//        // Parse JSON data
//        List<Team> teaminfo = JSONParserTeamInfo.parseJSON(jsonArray);
//
//        // Use the parsed data as needed
//        teamToSQL.insertDataIntoDB(teaminfo, "teaminfo");
//
//        jsonArray = fetchDataFromAPI("https://api.pandascore.co/codmw/teams");
//
//        teaminfo = JSONParserTeamInfo.parseJSON(jsonArray);
//
//        teamToSQL.insertDataIntoDB(teaminfo, "codteams");

//        jsonArray = fetchDataFromAPI("https://api.pandascore.co/csgo/teams");
////
//        teaminfo = JSONParserTeamInfo.parseJSON(jsonArray);
////
//        teamToSQL.insertDataIntoDB(teaminfo, "csteams");

//        jsonArray = fetchDataFromAPI("https://api.pandascore.co/lol/teams");
//
//        teaminfo = JSONParserTeamInfo.parseJSON(jsonArray);
//
//        teamToSQL.insertDataIntoDB(teaminfo, "lolteams");

//        jsonArray = fetchDataFromAPI("https://api.pandascore.co/valorant/teams");
//
//        teaminfo = JSONParserTeamInfo.parseJSON(jsonArray);
//
//        teamToSQL.insertDataIntoDB(teaminfo, "valteams");
//
//        JSONArray playerArray = fetchDataFromAPI("https://api.pandascore.co/players");
//
//        List<Player> playerList = JSONParserPlayer.parseJSON(playerArray);
//
//        playerToSQL.insertDataIntoDB(playerList, "playerlist");
//
//
////
////

        // // TESTING DATABASE TABLE CONTENTS
////
//        DatabaseUtils.displayTable("teaminfo");
//
//        System.out.println("\n\n\n");
//
//        DatabaseUtils.displayTable("csteams");
//
//        System.out.println("\n\n\n");
//
//        DatabaseUtils.displayTable("codteams");
//
//        System.out.println("\n\n\n");
//
//        DatabaseUtils.displayTable("valteams");
//
//        System.out.println("\n\n\n");
//
//        DatabaseUtils.displayTable("lolteams");
//
//        System.out.println("\n\n\n");



//
//        System.out.println("\n\n\n");


        DatabaseUtils.displayTable("playerlist");

    }

    // Define fetchDataFromAPI method to fetch data from the API and return a JSONArray
    static JSONArray fetchDataFromAPI(String url) throws IOException, InterruptedException {
        // Create URL object
        URI uri = URI.create(url);


        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + Test.bearerToken)
                .build();

        // Send HTTP request and handle response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            // Parse response as JSON array and return
            return new JSONArray(response.body());
        } else {
            throw new IOException("Failed to fetch data from API. Response code: " + response.statusCode());
        }

    }
}