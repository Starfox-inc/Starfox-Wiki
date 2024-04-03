import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;

public class Test {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/starfox";
    private static final String USERNAME = "starfoxUser";
    private static final String PASSWORD = "$tarfox123";
    private static final String bearerToken = "UzzOazUKLU9n3-7TaaT6SRtiU7lPjDVJ-B1rx76kO8hzvam17o8";
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
//         Call fetchDataFromAPI to get the JSONArray
//        JSONArray jsonArray = fetchDataFromAPI("https://api.pandascore.co/players");
//
//        // Parse JSON data
//        List<Player> playerlist = JSONParserPlayer.parseJSON(jsonArray);
//
//        // Use the parsed data as needed
//        playerToSQL.insertDataIntoDB(playerlist);

        DatabaseUtils.displayTable("teaminfo");

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