package integrationAPI;

import java.util.List;

import com.google.gson.Gson;

public class Serializer {

    // Serialize a single object to JSON
    public static <T> String serializeObject(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    // Serialize a list of objects to JSON
    public static <T> String serializeList(List<T> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }


}