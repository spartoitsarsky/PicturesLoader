package com.example.user.picturesloader;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13.09.2016.
 */
public class PicturesLab {
    private static final String API_KEY = "d70d68e6871786a7705343d08f37c41d";

    //secret 938dc73bf1d97303
    public byte[] getBytes(String inUrl) throws IOException {
        URL url = new URL(inUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream in = connection.getInputStream();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + " with url: " + url);
            }
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }

    }

    public String getString(String url) throws IOException {
        return new String(getBytes(url));

    }

    public List<Picture> fetchItems() {

        List<Picture> items = new ArrayList<>();
        try {
            Log.i("PICLAB ", "b4 http");
            String url = Uri.parse("https://api.flickr.com/services/rest/").buildUpon().appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY).appendQueryParameter("format", "json").appendQueryParameter("extras", "url_s").appendQueryParameter("nojsoncallback", "1").build().toString();
            String response = getString(url);
            Log.i("PICLAB ", "response: " + response);
            JSONObject jsonObject = new JSONObject(response);
            parseItems(items, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("PICLAB ", "ERROR!");
        }
        Log.i("PICLAB ", "items.size : " + items.size());

        return items;
    }

    private void parseItems(List<Picture> items, JSONObject jsonBody) throws IOException, JSONException {
        Log.i("PICLAB ", "parse start");
        JSONObject photosObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photosObject.getJSONArray("photo");
        Log.i("PICLAB ", "photoJsonArray.size: " + photoJsonArray.length());
        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);
            Picture pic = new Picture();
            if (photoJsonObject.has("url_s")) {
                pic.setUrl(photoJsonObject.getString("url_s"));
                items.add(pic);
            }

        }

    }
}
