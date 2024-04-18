package integrationAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.io.Serializable;


public class Match implements Serializable{
    private String begin_at;
    private boolean detailed_stats;
    private boolean draw;
    private String end_at;
    private boolean forfeit;

    private List<Game> games;

    private Integer id;

    private League league;

    private Integer leagueID;

    private Live livedata;

    private String matchType;

    private String name;

    private Integer numberOfGames;

    private Serie serie;

    private Integer serieID;

    private List<StreamsList> streamList;

    private Tournament tournament;

    private Integer tournament_id;

    private CurrentVideoGame videogame;
    private Integer game_advantage;

    public String getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(String begin_at) {
        this.begin_at = begin_at;
    }

    public boolean isDetailed_stats() {
        return detailed_stats;
    }

    public void setDetailed_stats(boolean detailed_stats) {
        this.detailed_stats = detailed_stats;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public boolean isForfeit() {
        return forfeit;
    }

    public void setForfeit(boolean forfeit) {
        this.forfeit = forfeit;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Integer getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(Integer leagueID) {
        this.leagueID = leagueID;
    }

    public Live getLivedata() {
        return livedata;
    }

    public void setLivedata(Live livedata) {
        this.livedata = livedata;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getSerieID() {
        return serieID;
    }

    public void setSerieID(Integer serieID) {
        this.serieID = serieID;
    }

    public List<StreamsList> getStreamList() {
        return streamList;
    }

    public void setStreamList(List<StreamsList> streamList) {
        this.streamList = streamList;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Integer getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(Integer tournament_id) {
        this.tournament_id = tournament_id;
    }

    public CurrentVideoGame getVideogame() {
        return videogame;
    }

    public void setVideogame(CurrentVideoGame videogame) {
        this.videogame = videogame;
    }

    public Integer getGame_advantage() {
        return game_advantage;
    }

    public void setGame_advantage(Integer game_advantage) {
        this.game_advantage = game_advantage;
    }

    public Match() {

    }

    public Match(String begin_at, boolean detailed_stats, boolean draw, String end_at, boolean forfeit, List<Game> games, Integer id,
                 League league, Integer leagueID, Live livedata, String matchType, String name,
                 Integer numberOfGames, Serie serie, Integer serieID, List<StreamsList> streamList, Tournament tournament, Integer tournament_id,
                 CurrentVideoGame videogame, int game_advantage) {
        this.begin_at = begin_at;
        this.detailed_stats = detailed_stats;
        this.draw = draw;
        this.end_at = end_at;
        this.forfeit = forfeit;
        this.games = games;
        this.id = id;
        this.league = league;
        this.leagueID = leagueID;
        this.livedata = livedata;
        this.matchType = matchType;
        this.name = name;
        this.numberOfGames = numberOfGames;
        this.serie = serie;
        this.serieID = serieID;
        this.streamList = streamList;
        this.tournament = tournament;
        this.tournament_id = tournament_id;
        this.videogame = videogame;
        this.game_advantage = game_advantage;
    }


    public static List<Match> parseMatch(JSONArray matchArray) {
        List<Match> matches = new ArrayList<>();

        for (int i = 0; i < matchArray.length(); i++) {
            try {
                JSONObject matchObject = matchArray.getJSONObject(i);
                String begin_at = matchObject.optString("begin_at");
                boolean detailed_stats = matchObject.optBoolean("detailed_stats");
                boolean draw = matchObject.optBoolean("draw");
                String end_at = matchObject.optString("end_at");
                boolean forfeit = matchObject.optBoolean("forfeit");
                List<Game> games = Game.parseGame(matchObject.optJSONArray("games"));
                Integer id = matchObject.optInt("id");
                League league = League.parseLeague(matchObject.optJSONObject("league"));
                Integer leagueID = matchObject.optInt("league_id");
                Live livedata = Live.parseLive(matchObject.optJSONObject("live"));
                String matchType = matchObject.optString("match_type");
                String name = matchObject.optString("name");
                Integer numberOfGames = matchObject.optInt("number_of_games");
                Serie serie = Serie.parseSerie(matchObject.optJSONObject("serie"));
                Integer serieID = matchObject.optInt("serie_id");
                List<StreamsList> streamList = StreamsList.parseStreamList(matchObject.optJSONArray("streams_list"));
                Tournament tournament = Tournament.parseTournament(matchObject.optJSONObject("tournament"));
                Integer tournament_id = matchObject.optInt("tournament_id");
                CurrentVideoGame videogame = CurrentVideoGame.parseCurrentGame(matchObject.optJSONObject("videogame"));
                int game_advantage = matchObject.optInt("game_advantage");

                Match match = new Match(begin_at, detailed_stats, draw, end_at, forfeit, games, id,
                        league, leagueID, livedata, matchType, name,
                        numberOfGames, serie, serieID, streamList, tournament, tournament_id,
                        videogame, game_advantage);

                matches.add(match);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return matches;
    }
}