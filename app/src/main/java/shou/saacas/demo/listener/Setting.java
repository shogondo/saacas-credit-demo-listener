package shou.saacas.demo.listener;

import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Setting {
    private static Setting instance;

    private String apiKey;

    private Setting() {
    }

    public static Setting getInstance() {
        if (instance == null) {
            instance = null;
        }
        return instance;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void load(AssetManager manager) {
        try {
            JSONObject json = new JSONObject(loadContent(manager));
            apiKey = json.getString("api_key");
        } catch (JSONException e) {
            throw new RuntimeException("Failed to parse JSON.", e);
        }
    }

    private String loadContent(AssetManager manager) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(manager.open("setting.json")));

            String line;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }

            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load setting.", e);
        }
    }
}
