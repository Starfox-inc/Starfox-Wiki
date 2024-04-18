import org.json.JSONException;
import org.json.JSONObject;

public class CurrentTeam {
    private String acronym;

    private int id;

    private String imageUrl;

    private String location;

    private String name;

    private String slug;

    public CurrentTeam() {

    }


    // Getters
    public String getAcronym(){
        return acronym;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getLocation(){
        return location;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setAcronym(String acronym){
        this.acronym = acronym;
    }
    public CurrentTeam getCurrent_videoGame() {
        return this;
    }


    public CurrentTeam(String acronym, int id, String imageUrl, String location, String name, String slug) {
        this.acronym = acronym;
        this.id = id;
        this.imageUrl = imageUrl;
        this.location = location;
        this.name = name;
        this.slug = slug;
    }

    static CurrentTeam parseCurrentTeam(JSONObject teamObject) throws JSONException {
        return new CurrentTeam(
                teamObject.optString("acronym"),
                teamObject.optInt("id"),
                teamObject.optString("image_url"),
                teamObject.optString("location"),
                teamObject.getString("name"),
                teamObject.getString("slug")
        );
    }
}

