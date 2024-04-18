package integrationAPI;

import org.json.JSONObject;

import java.io.Serializable;

public class Tournament implements Serializable {
    private String begin_at;
    private boolean detailed_stats;
    private String end_at;
    private boolean has_bracket;
    private int id;
    private int league_id;
    private boolean live_supported;
    private String modified_at;
    private String name;
    private String prizepool;
    private int serie_id;
    private String slug;
    private String tier;
    private Integer winner_id;
    private String winner_type;

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

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public boolean isHas_bracket() {
        return has_bracket;
    }

    public void setHas_bracket(boolean has_bracket) {
        this.has_bracket = has_bracket;
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

    public boolean isLive_supported() {
        return live_supported;
    }

    public void setLive_supported(boolean live_supported) {
        this.live_supported = live_supported;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrizepool() {
        return prizepool;
    }

    public void setPrizepool(String prizepool) {
        this.prizepool = prizepool;
    }

    public int getSerie_id() {
        return serie_id;
    }

    public void setSerie_id(int serie_id) {
        this.serie_id = serie_id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
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

    public Tournament(String begin_at, boolean detailed_stats, String end_at, boolean has_bracket, int id,
                      int league_id, boolean live_supported, String modified_at, String name, String prizepool,
                      int serie_id, String slug, String tier, Integer winner_id, String winner_type) {
        this.begin_at = begin_at;
        this.detailed_stats = detailed_stats;
        this.end_at = end_at;
        this.has_bracket = has_bracket;
        this.id = id;
        this.league_id = league_id;
        this.live_supported = live_supported;
        this.modified_at = modified_at;
        this.name = name;
        this.prizepool = prizepool;
        this.serie_id = serie_id;
        this.slug = slug;
        this.tier = tier;
        this.winner_id = winner_id;
        this.winner_type = winner_type;
    }

    public static Tournament parseTournament(JSONObject tournamentObject) {
        String begin_at = tournamentObject.optString("begin_at");
        boolean detailed_stats = tournamentObject.optBoolean("detailed_stats");
        String end_at = tournamentObject.optString("end_at");
        boolean has_bracket = tournamentObject.optBoolean("has_bracket");
        int id = tournamentObject.optInt("id");
        int league_id = tournamentObject.optInt("league_id");
        boolean live_supported = tournamentObject.optBoolean("live_supported");
        String modified_at = tournamentObject.optString("modified_at");
        String name = tournamentObject.optString("name");
        String prizepool = tournamentObject.optString("prizepool");
        int serie_id = tournamentObject.optInt("serie_id");
        String slug = tournamentObject.optString("slug");
        String tier = tournamentObject.optString("tier");
        Integer winner_id = tournamentObject.isNull("winner_id") ? null : tournamentObject.optInt("winner_id");
        String winner_type = tournamentObject.optString("winner_type");

        return new Tournament(begin_at, detailed_stats, end_at, has_bracket, id, league_id, live_supported, modified_at, name,
                prizepool, serie_id, slug, tier, winner_id, winner_type);
    }
}
