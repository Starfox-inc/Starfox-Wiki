public class CurrentVideoGame {
    private int id;
    private String name;
    private String slug;

    // Constructor
    public CurrentVideoGame(int id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    // Getters
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
    public void setId(int id) {
        this.id = id;
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
}
