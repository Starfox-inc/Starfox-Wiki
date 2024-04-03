import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JSONParserPlayer {

    public static List<Player> parseJSON(JSONArray jsonArray) {
        List<Player> playerList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject playerObject = jsonArray.getJSONObject(i);
                Player player = parsePlayer(playerObject);
                playerList.add(player);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing error if needed
        }

        return playerList;
    }

    private static Player parsePlayer(JSONObject playerObject) throws JSONException {
        // Parse player fields
        String name = playerObject.getString("name");
        String slug = playerObject.getString("slug");
        boolean active = playerObject.getBoolean("active");
        int age = playerObject.optInt("age", 0); // Default to 0 if "age" is not present
        String first_name = playerObject.optString("first_name"); // Default to null if not present
        String last_name = playerObject.optString("last_name"); // Default to null if not present
        String nationality = playerObject.optString("nationality"); // Default to null if not present
        String role = playerObject.optString("role"); // Default to null if not present
        int playerId = playerObject.getInt("id");
        String imageUrl = playerObject.optString("image_url", null); // Default to null if "image_url" is not present

        // Parse "current_team" if present
        CurrentTeam currentTeam = null;
        if (playerObject.has("current_team") && !playerObject.isNull("current_team")) {
            JSONObject currentTeamObject = playerObject.getJSONObject("current_team");
            // Parse fields of currentTeamObject
            String acronym = currentTeamObject.optString("acronym", null); // Default to null if "acronym" is not present
            int teamId = currentTeamObject.getInt("id");
            String teamImageUrl = currentTeamObject.optString("image_url", null); // Default to null if "image_url" is not present
            String teamLocation = currentTeamObject.optString("location", null);
            String teamName = currentTeamObject.optString("name", null);
            String teamSlug = currentTeamObject.optString("slug", null);
            // Create CurrentTeam object
            currentTeam = new CurrentTeam(acronym, teamId, teamImageUrl, teamLocation, teamName, teamSlug);
        }

        // Parse "current_videogame" if present
        CurrentVideoGame currentVideoGame = null;
        if (playerObject.has("current_videogame") && !playerObject.isNull("current_videogame")) {
            JSONObject currentVideoGameObject = playerObject.getJSONObject("current_videogame");
            // Parse fields of currentVideoGameObject
            int gameId = currentVideoGameObject.getInt("id");
            String gameName = currentVideoGameObject.getString("name");
            String gameSlug = currentVideoGameObject.getString("slug");
            // Create CurrentVideoGame object
            currentVideoGame = new CurrentVideoGame(gameId, gameName, gameSlug);
        }

        // Create and return Player object
        return new Player(active, age, first_name, playerId, imageUrl, last_name, name, nationality, role, slug, currentTeam, currentVideoGame);
    }
}
