package integrationAPI;

import org.json.JSONException;
import org.json.JSONObject;

public class Endpoints {
    private String begin_at;

    private int last_active;

    private int match_id;

    private boolean open;

    private String type;

    private String url;

    public Endpoints(String begin_at, int last_active, int match_id, boolean open, String type, String url) {
        this.begin_at = begin_at;
        this.last_active = last_active;
        this.match_id = match_id;
        this.open = open;
        this.type = type;
        this.url = url;
    }
    static Endpoints parseEndpoint(JSONObject endPointObject) throws JSONException {
        return new Endpoints(
                endPointObject.optString("begin_at"),
                endPointObject.optInt("last_active"),
                endPointObject.optInt("match_id"),
                endPointObject.optBoolean("open"),
                endPointObject.optString("events"),
                endPointObject.optString("url")
                );
     }

    public String getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(String begin_at) {
        this.begin_at = begin_at;
    }

    public int getLast_active() {
        return last_active;
    }

    public void setLast_active(int last_active) {
        this.last_active = last_active;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
