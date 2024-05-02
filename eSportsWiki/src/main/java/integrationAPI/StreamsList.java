package integrationAPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;

@Data
public class StreamsList implements Serializable{
    private String embed_url;
    private String language;
    private boolean main;
    private boolean official;
    private String raw_url;

    public StreamsList(String embed_url, String language, boolean main, boolean official, String raw_url) {
        this.embed_url = embed_url;
        this.language = language;
        this.main = main;
        this.official = official;
        this.raw_url = raw_url;
    }

    // Getters and setters

    public static List<StreamsList> parseStreamList(JSONArray streamsListArray) {
        List<StreamsList> streamsList = new ArrayList<>();
        if (streamsListArray != null) {
            for (int i = 0; i < streamsListArray.length(); i++) {
                try {
                    JSONObject streamObject = streamsListArray.getJSONObject(i);
                    String embed_url = streamObject.optString("embed_url");
                    String language = streamObject.optString("language");
                    boolean main = streamObject.optBoolean("main");
                    boolean official = streamObject.optBoolean("official");
                    String raw_url = streamObject.optString("raw_url");

                    StreamsList stream = new StreamsList(embed_url, language, main, official, raw_url);
                    streamsList.add(stream);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return streamsList;
    }

    public String toString(){
        return "raw_url: " + raw_url;
    }
}

