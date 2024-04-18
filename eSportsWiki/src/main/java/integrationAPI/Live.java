package integrationAPI;

import org.json.JSONObject;

import java.io.Serializable;

public class Live implements Serializable {
    private String opens_at;

    private boolean supported;

    private String url;

    public Live(String opens_at, boolean supported, String url) {
        this.opens_at = opens_at;
        this.supported = supported;
        this.url = url;
    }

    public Live(){

    }

    public static Live parseLive(JSONObject liveObject) {
        String opens = liveObject.optString("opens_at");
        String url = liveObject.optString("url");
        boolean supported = liveObject.optBoolean("supported");
        return new Live(opens, supported, url);
    }

    public String getOpens_at() {
        return opens_at;
    }

    public void setOpens_at(String opens_at) {
        this.opens_at = opens_at;
    }

    public boolean isSupported() {
        return supported;
    }

    public void setSupported(boolean supported) {
        this.supported = supported;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
