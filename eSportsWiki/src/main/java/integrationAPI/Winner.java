package integrationAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Winner implements Serializable {
    private int id;
    private String type;

    // Getters and setters

    public Winner(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Winner() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private static List<Winner> parseWinner(JSONArray winnerArray){
        List<Winner> winner = new ArrayList<>();
        for(int i = 0; i < winnerArray.length(); i++){
            JSONObject winnerObject = winnerArray.getJSONObject(i);
            Winner winners = new Winner();
            winners.setType(winnerObject.getString("type"));
            winners.setId(winnerObject.getInt("id"));
            winner.add(winners);
        }
        return winner;
    }
}