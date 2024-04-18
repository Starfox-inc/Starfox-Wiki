package integrationAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class League implements Serializable {
    private Integer id;
    private String image_url;

    private String name;

    private String slug;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public League(Integer id, String image_url, String name, String slug, String url) {
        this.id = id;
        this.image_url = image_url;
        this.name = name;
        this.slug = slug;
        this.url = url;
    }

    public static League parseLeague(JSONObject leagueObject) throws JSONException {
        Integer id = leagueObject.optInt("id");
        String image_url = leagueObject.optString("image_url");
        String name = leagueObject.optString("name");
        String slug = leagueObject.optString("slug");
        String url = leagueObject.optString("url");

        return new League(id, image_url, name, slug, url);
    }
}
