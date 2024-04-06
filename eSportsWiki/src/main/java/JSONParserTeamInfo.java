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
                JSONObject teamObject = jsonArray.optJSONObject(i);

                // Parse team data from JSON object
                int id = teamObject.optInt("id");
                String acronym = teamObject.optString("acronym");
                JSONObject currentVideoGameObject = teamObject.optJSONObject("current_videogame");
                CurrentVideoGame currentVideoGame = CurrentVideoGame.parseCurrentGame(currentVideoGameObject);
                String image_url = teamObject.optString("image_url");
                String location = teamObject.optString("location");
                String name = teamObject.optString("name");
                String slug = teamObject.optString("slug");

//                // Parse players for the team
//                JSONArray playersArray = teamObject.optJSONArray("players");
//                List<Player> players = JSONParserPlayer.parseJSON(playersArray);

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