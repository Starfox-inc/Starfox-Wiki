package integrationAPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Game implements Serializable {
    private String begin_at;
    private boolean complete;
    private String end_at;
    // Other fields...
    private boolean finished;
    private boolean forfeit;
    private int id;
    private int length;
    private int match_id;
    private int position;
    private String status;

    public Game() {

    }

    public String getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(String begin_at) {
        this.begin_at = begin_at;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isForfeit() {
        return forfeit;
    }

    public void setForfeit(boolean forfeit) {
        this.forfeit = forfeit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    private Winner winner;

    public Game(String begin_at, boolean complete, String end_at, boolean finished,
                boolean forfeit, int id, int length, int match_id, int position, String status, Winner winner) {
        this.begin_at = begin_at;
        this.complete = complete;
        this.end_at = end_at;
        this.finished = finished;
        this.forfeit = forfeit;
        this.id = id;
        this.length = length;
        this.match_id = match_id;
        this.position = position;
        this.status = status;
        this.winner = winner;
    }


    static List<Game> parseGame(JSONArray gamesArray) {
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < gamesArray.length(); i++) {
            JSONObject gameObject = gamesArray.getJSONObject(i);
            Game game = new Game();
            game.setBegin_at(gameObject.optString("begin_at"));
            game.setComplete(gameObject.optBoolean("complete"));
            game.setForfeit(gameObject.optBoolean("forfeit"));
            game.setId(gameObject.optInt("id"));
            game.setLength(gameObject.optInt("length"));
            game.setMatch_id(gameObject.optInt("match_id"));
            game.setPosition(gameObject.optInt("position"));
            game.setStatus(gameObject.optString("status"));
            JSONObject winnerObject = gameObject.optJSONObject("winner");
            if (winnerObject != null) {
                Winner winner = new Winner();
                winner.setId(winnerObject.optInt("id"));
                winner.setType(winnerObject.optString("type"));
                game.setWinner(winner);
            }
            games.add(game);
        }
        return games;
    }
}