package com.Starfox.EsportsWiki.database;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Starfox.EsportsWiki.model.CurrentTeam;
import integrationAPI.CurrentVideoGame;
import com.Starfox.EsportsWiki.model.Player;
public class JSONParserPlayer {
    public static List<Player> parseJSON(JSONArray jsonArray) {
        List<Player> playerList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject playerObject = jsonArray.optJSONObject(i);

                boolean active = playerObject.optBoolean("active");
                int age = playerObject.optInt("age");

                // Handle "current_team" object
                JSONObject currentTeamObject = playerObject.optJSONObject("current_team");
                CurrentTeam currentTeam = null;
                if (currentTeamObject != null) {
                    currentTeam = CurrentTeam.parseCurrentTeam(currentTeamObject);
                }

                // Handle "current_videogame" object
                JSONObject currentVideoGameObject = playerObject.optJSONObject("current_videogame");
                CurrentVideoGame currentVideoGame = null;
                if (currentVideoGameObject != null) {
                    int gameId = currentVideoGameObject.optInt("id");
                    String gameName = currentVideoGameObject.optString("name");
                    String gameSlug = currentVideoGameObject.optString("slug");
                    currentVideoGame = new CurrentVideoGame(gameId, gameName, gameSlug);
                }

                String first_name = playerObject.optString("first_name");
                int playerId = playerObject.optInt("id");
                String image_url = playerObject.optString("image_url");
                String last_name = playerObject.optString("last_name");
                String name = playerObject.optString("name");
                String nationality = playerObject.optString("nationality");
                String role = playerObject.optString("role");
                String slug = playerObject.optString("slug");

                Player player = new Player(active, age, first_name, playerId, image_url, last_name, name, nationality, role, slug, currentTeam, currentVideoGame);
                playerList.add(player);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("An error occurred while parsing player JSON data.");
        }

        return playerList;
    }
}