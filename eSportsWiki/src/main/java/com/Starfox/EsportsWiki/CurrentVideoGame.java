import org.json.JSONException;
import org.json.JSONObject;

public class CurrentVideoGame {
    private Integer id;
    private String name;
    private String slug;

    // Constructor
    public CurrentVideoGame(Integer id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public CurrentVideoGame() {
        this.id = null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setId() {
        this.id = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public CurrentVideoGame getCurrent_videoGame() {
        return this;
    }

    static CurrentVideoGame parseCurrentGame(JSONObject gameObject) throws JSONException {
        return new CurrentVideoGame(
                gameObject.getInt("id"),
                gameObject.getString("name"),
                gameObject.getString("slug")
        );
    }
}
