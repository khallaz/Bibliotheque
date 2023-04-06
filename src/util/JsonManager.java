package util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonManager<T> {
    private String fileName;

    public JsonManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveData(List<T> dataList, DataConverter<T> converter) {
        JSONArray jsonArray = new JSONArray();

        for (T data : dataList) {
            JSONObject jsonObject = converter.toJson(data);
            jsonArray.add(jsonObject);
        }

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> loadData(DataConverter<T> converter) {
        List<T> dataList = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {
            Object obj = parser.parse(reader);

            JSONArray jsonArray = (JSONArray) obj;
            for (Object json : jsonArray) {
                dataList.add(converter.fromJson((JSONObject) json));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}

