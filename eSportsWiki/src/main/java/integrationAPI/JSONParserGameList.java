import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParserGameList {
    public static List<CurrentVideoGame> parseJSON(JSONArray jsonArray){
        List<CurrentVideoGame> gameList = new ArrayList<>();
        try{
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject gameObject = jsonArray.optJSONObject(i);
                int id = gameObject.optInt("id");
                        String name = gameObject.optString("name");
                        String slug = gameObject.optString("slug");
                        CurrentVideoGame game = new CurrentVideoGame(id, name, slug);
                        gameList.add(game);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return gameList;
    }
}
