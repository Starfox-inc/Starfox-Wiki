package integrationAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;

public class Test {
    //please name your database starfox and create a new user with the username and password to maintain consistency across code
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String USERNAME = "starfoxUser";
    private static final String PASSWORD = "$tarfox123";
    private static final String bearerToken = "UzzOazUKLU9n3-7TaaT6SRtiU7lPjDVJ-B1rx76kO8hzvam17o8";
    public static void main(String[] args) throws IOException, InterruptedException, SQLException, URISyntaxException {

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
       List<String> current_videogameColumns= List.of("id INT AUTO_INCREMENT PRIMARY KEY",
                                                       "name VARCHAR(255)",
                                                       "slug VARCHAR(255)"
                                                       );

        List<String> playerListColumns = List.of(
                "playerId INT NOT NULL PRIMARY KEY",
                "active TINYINT(1)",
                "age INT",
                "first_name VARCHAR(255)",
                "last_name VARCHAR(255)",
                "name VARCHAR(255)",
                "nationality VARCHAR(255)",
                "role VARCHAR(255)",
                "slug VARCHAR(255)",
                "image_url VARCHAR(255)",
                "teaminfo_teamID INT",
                "current_videogame_gameID INT"
        );

       List<String> teamInfoColumns = List.of(
               "teamID INT NOT NULL PRIMARY KEY",
               "acronym VARCHAR(255)",
               "current_videogame_id INT",
               "current_videogame_name VARCHAR(255)",
               "current_videogame_slug VARCHAR(255)",
               "image_url VARCHAR(255)",
               "name VARCHAR(255)",
               "location VARCHAR(255)",
               "slug VARCHAR(255)"
       );
/*        List<String> userColumns = List.of(
                "user_id INT AUTO_INCREMENT PRIMARY KEY",
                "username VARCHAR(30) NOT NULL UNIQUE",
                "email VARCHAR(255) NOT NULL UNIQUE",
                "password_hash VARCHAR(60) NOT NULL"
        );
*/
        List<String> matchesColumns = List.of(
                "begin_at TIMESTAMP",
                "detailed_stats BOOLEAN",
                "draw BOOLEAN",
                "end_at TIMESTAMP",
                "forfeit BOOLEAN",
                "games VARCHAR(3000)", // Assuming games will be stored as a JSON string
                "id INT PRIMARY KEY",
                "league VARCHAR(1000)", // Assuming league will be stored as a JSON string
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


//         // CREATE TABLES USING TABLE DEFINITIONS FROM ABOVE
        integrationAPI.CreateTables.createTable("playerlist", playerListColumns);
       integrationAPI.CreateTables.createTable("current_videogame", current_videogameColumns);
       integrationAPI.CreateTables.createTable("teaminfo", teamInfoColumns);
       integrationAPI.CreateTables.createTable("codteams", teamInfoColumns);
       integrationAPI.CreateTables.createTable("csteams", teamInfoColumns);
       integrationAPI.CreateTables.createTable("lolteams", teamInfoColumns);
       integrationAPI.CreateTables.createTable("valteams", teamInfoColumns);
       integrationAPI.CreateTables.createTable("runningMatchList", matchesColumns);
       integrationAPI.CreateTables.createTable("codMatches", matchesColumns);
       integrationAPI.CreateTables.createTable("csMatches", matchesColumns);
       integrationAPI.CreateTables.createTable("valMatches", matchesColumns);
       integrationAPI.CreateTables.createTable("lolMatches", matchesColumns);
       integrationAPI.CreateTables.createTable("codPastMatches", matchesColumns);
       integrationAPI.CreateTables.createTable("lolPastMatches", matchesColumns);
       integrationAPI.CreateTables.createTable("valPastMatches", matchesColumns);
       integrationAPI.CreateTables.createTable("csPastMatches", matchesColumns);

//        Filling tables - starting with teaminfo table
//        Call to fetchDataFromAPI to get the JSONArray
       JSONArray jsonArray = fetchDataFromAPI("https://api.pandascore.co/teams");


       // Parse JSON data
       List<integrationAPI.Team> teaminfo = integrationAPI.JSONParserTeamInfo.parseJSON(jsonArray);

       // Use the parsed data as needed
       integrationAPI.teamToSQL.insertDataIntoDB(teaminfo, "teaminfo");

       // COD TEAMS

       jsonArray = fetchDataFromAPI("https://api.pandascore.co/codmw/teams");

       teaminfo = integrationAPI.JSONParserTeamInfo.parseJSON(jsonArray);

       integrationAPI.teamToSQL.insertDataIntoDB(teaminfo, "codteams");
       // CS TEAMS
       jsonArray = fetchDataFromAPI("https://api.pandascore.co/csgo/teams");
//
       teaminfo = integrationAPI.JSONParserTeamInfo.parseJSON(jsonArray);
//
       integrationAPI.teamToSQL.insertDataIntoDB(teaminfo, "csteams");
       // LOL TEAMS
       jsonArray = fetchDataFromAPI("https://api.pandascore.co/lol/teams");

       teaminfo = integrationAPI.JSONParserTeamInfo.parseJSON(jsonArray);

       integrationAPI.teamToSQL.insertDataIntoDB(teaminfo, "lolteams");
       // VAL TEAMS
       jsonArray = fetchDataFromAPI("https://api.pandascore.co/valorant/teams");

       teaminfo = integrationAPI.JSONParserTeamInfo.parseJSON(jsonArray);

       integrationAPI.teamToSQL.insertDataIntoDB(teaminfo, "valteams");

//        // integrationAPI.Player list
        JSONArray playerArray = fetchDataFromAPI("https://api.pandascore.co/players");

        List<integrationAPI.Player> playerList = integrationAPI.JSONParserPlayer.parseJSON(playerArray);

        integrationAPI.playerToSQL.insertDataIntoDB(playerList, "playerlist");
//
//        // game list
       JSONArray gameListArray = fetchDataFromAPI("https://api.pandascore.co/videogames");

       List<CurrentVideoGame> gameList = integrationAPI.JSONParserGameList.parseJSON(gameListArray);

       integrationAPI.gameListToSQL.insertDataIntoDB(gameList);
//
       JSONArray runningMatches = fetchDataFromAPI("https://api.pandascore.co/matches/running");

       List<Match> runningMatchList = Match.parseMatch(runningMatches);

       matchToSQL.insertDataIntoDB(runningMatchList, "runningMatchList");

       JSONArray codMatches = fetchDataFromAPI("https://api.pandascore.co/codmw/matches");

       List<Match> codMatchList = Match.parseMatch(codMatches);

       matchToSQL.insertDataIntoDB(codMatchList, "codMatches");

       JSONArray csMatches = fetchDataFromAPI("https://api.pandascore.co/csgo/matches");

       List<Match> csMatchList = Match.parseMatch(csMatches);

       matchToSQL.insertDataIntoDB(csMatchList, "csMatches");

       JSONArray valMatches = fetchDataFromAPI("https://api.pandascore.co/valorant/matches");

       List<Match> valMatchList = Match.parseMatch(valMatches);

       matchToSQL.insertDataIntoDB(valMatchList, "valMatches");

       JSONArray lolMatches = fetchDataFromAPI("https://api.pandascore.co/lol/matches");

       List<Match> lolMatchList = Match.parseMatch(lolMatches);

       matchToSQL.insertDataIntoDB(lolMatchList, "lolMatches");

       JSONArray codPastMatches = fetchDataFromAPI("https://api.pandascore.co/codmw/matches/past");

       List<Match> codPastMatchList = Match.parseMatch(codPastMatches);

       matchToSQL.insertDataIntoDB(codPastMatchList, "codPastMatches");

       JSONArray csPastMatches = fetchDataFromAPI("https://api.pandascore.co/csgo/matches/past");

       List<Match> csPastMatchList = Match.parseMatch(csPastMatches);

       matchToSQL.insertDataIntoDB(csPastMatchList, "csPastMatches");

       JSONArray valPastMatches = fetchDataFromAPI("https://api.pandascore.co/valorant/matches/past");

       List<Match> valPastMatchList = Match.parseMatch(valPastMatches);

       matchToSQL.insertDataIntoDB(valPastMatchList, "valPastMatches");

       JSONArray lolPastMatches = fetchDataFromAPI("https://api.pandascore.co/lol/matches/past");

       List<Match> lolPastMatchList = Match.parseMatch(lolPastMatches);

       matchToSQL.insertDataIntoDB(lolPastMatchList, "lolPastMatches");

        // // TESTING DATABASE TABLE CONTENTS
////
//        integrationAPI.DatabaseUtils.displayTable("teaminfo");
//
//        System.out.println("\n\n\n");
//
//        integrationAPI.DatabaseUtils.displayTable("csteams");
//
//        System.out.println("\n\n\n");
//
//        integrationAPI.DatabaseUtils.displayTable("codteams");
//
//        System.out.println("\n\n\n");
//
//        integrationAPI.DatabaseUtils.displayTable("valteams");
//
//        System.out.println("\n\n\n");
//
//        integrationAPI.DatabaseUtils.displayTable("lolteams");
//
//        System.out.println("\n\n\n");


//        integrationAPI.DatabaseUtils.displayTable("current_videogame");
//
//        System.out.println("\n\n\n");


        integrationAPI.DatabaseUtils.displayTable("playerlist");
        //integrationAPI.DatabaseUtils.displayTable("runningMatchList");

    }

    // Define fetchDataFromAPI method to fetch data from the API and return a JSONArray
    static final int MAX_PAGES = 10; // Maximum number of pages to fetch

    static JSONArray fetchDataFromAPI(String url) throws IOException, InterruptedException, URISyntaxException {
        JSONArray allData = new JSONArray();
        int pageNumber = 1;
        int pageSize = 50; // Default page size
        boolean hasMorePages = true;

        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();

        while (pageNumber <= MAX_PAGES && hasMorePages) {
            // Create URI with page number
            URI uri = new URI(url + "?page[number]=" + pageNumber + "&page[size]=" + pageSize);

            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Authorization", "Bearer " + Test.bearerToken)
                    .build();

            // Send HTTP request and handle response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Parse response as JSON array and add to allData
                JSONArray responseData = new JSONArray(response.body());
                allData.putAll(responseData);

                // Check if there are more pages
                if (responseData.length() < pageSize) {
                    hasMorePages = false;
                } else {
                    pageNumber++;
                }
            } else {
                throw new IOException("Failed to fetch data from API. Response code: " + response.statusCode());
            }
        }

        return allData;
    }
}