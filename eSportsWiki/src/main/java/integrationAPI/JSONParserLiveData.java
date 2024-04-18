//package integrationAPI;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class JSONParserLiveData {
//    public static List<LiveData> parseJSON(JSONArray jsonArray){
//        List<LiveData> liveDataList = new ArrayList<>();
//        try{
//            for(int i = 0; i < jsonArray.length(); i++){
//                JSONObject gameObject = jsonArray.optJSONObject(i);
//                JSONObject endpoint = gameObject.optJSONObject("endpoints");
//                Endpoints endPointObject = null;
//                if (endPointObject != null) {
//                    endPointObject = Endpoints.parseEndpoint(endpoint);
//                }
//                JSONObject match = gameObject.optJSONObject("match");
//                Match matchObject = null;
//                if (matchObject != null) {
//                    matchObject = Match.parseMatch(match);
//                }
//                int id = gameObject.optInt("id");
//                int league_id = gameObject.optInt("league_id");
//                String name = gameObject.optString("name");
//                String slug = gameObject.optString("slug");
//                int numGames = gameObject.optInt("number_of_games");
//                LiveData data = new LiveData(endPointObject, matchObject, id, league_id, leagueObject, liveObject, mapPicksObject, name,
//                        numGames, opponentsObject, resultsObject, videoGameObject, winnerObject, streamListObject) ;
//                liveDataList.add(data);
//            }
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
//        return liveDataList;
//    }
//}
