package integrationAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Serie implements Serializable {
    private String begin_at;
    private String end_at;
    private String full_name;
    private int id;
    private int league_id;
    private String name;
    private String season;
    private String slug;
    private Integer winner_id;
    private String winner_type;
    private int year;

    // Constructor
    public Serie(String begin_at, String end_at, String full_name, int id, int league_id, String name, String season, String slug, Integer winner_id, String winner_type, int year) {
        this.begin_at = begin_at;
        this.end_at = end_at;
        this.full_name = full_name;
        this.id = id;
        this.league_id = league_id;
        this.name = name;
        this.season = season;
        this.slug = slug;
        this.winner_id = winner_id;
        this.winner_type = winner_type;
        this.year = year;
    }

    // Getters and setters
    public String getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(String begin_at) {
        this.begin_at = begin_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(Integer winner_id) {
        this.winner_id = winner_id;
    }

    public String getWinner_type() {
        return winner_type;
    }

    public void setWinner_type(String winner_type) {
        this.winner_type = winner_type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static Serie parseSerie(JSONObject serieObject) throws JSONException {
        String begin_at = serieObject.optString("begin_at");
        String end_at = serieObject.optString("end_at");
        String full_name = serieObject.optString("full_name");
        int id = serieObject.optInt("id");
        int league_id = serieObject.optInt("league_id");
        String name = serieObject.optString("name");
        String season = serieObject.optString("season");
        String slug = serieObject.optString("slug");
        Integer winner_id = serieObject.optInt("winner_id");
        String winner_type = serieObject.optString("winner_type");
        int year = serieObject.optInt("year");

        return new Serie(begin_at, end_at, full_name, id, league_id, name, season, slug, winner_id, winner_type, year);
    }
}