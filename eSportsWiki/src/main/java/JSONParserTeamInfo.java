import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParserTeamInfo {
    public static List<Team> parseJSON(JSONArray jsonArray) {
        List<Team> teamList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject teamObject = jsonArray.getJSONObject(i);

                // Parse team data from JSON object
                String acronym = null; // Initialize imageUrl variable
// Check if "image_url" exists and is not null
                if (!teamObject.isNull("acronym")) {
                    // "image_url" exists and is not null, so retrieve it as a string
                    acronym = teamObject.getString("acronym");
                }
                JSONObject currentVideoGameObject = teamObject.getJSONObject("current_videogame");
                CurrentVideoGame currentVideoGame = new CurrentVideoGame(currentVideoGameObject.getInt("id"), currentVideoGameObject.getString("name"), currentVideoGameObject.getString("slug"));
                int id = teamObject.getInt("id");
                String image_url = null; // Initialize imageUrl variable
// Check if "image_url" exists and is not null


                if (!teamObject.isNull("image_url")) {
                    // "image_url" exists and is not null, so retrieve it as a string
                    image_url = teamObject.getString("image_url");
                }


                String location = teamObject.getString("location");
                String name = teamObject.getString("name");
                String slug = teamObject.getString("slug");

                // Create Team object and add to list
                Team team = new Team(acronym, currentVideoGame, id, image_url, location, name, slug);
                teamList.add(team);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return teamList;
    }
}
