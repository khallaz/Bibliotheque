package util;

import org.json.simple.JSONObject;

public interface DataConverter<T> {
    JSONObject toJson(T data);
    T fromJson(JSONObject jsonObject);
}

