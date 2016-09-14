package com.example.user.picturesloader;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public void fetchItems() {
        try {
            String url= Uri.parse("https://api.flickr.com/services/rest/").buildUpon().appendQueryParameter("method","flickr.photos.getRecent")
                    .appendQueryParameter("api_key",API_KEY).appendQueryParameter("format","json").build().toString();
            String response=getString(url);
            Log.i("JSON: ",response);
        } catch (Exception e) {

        }
    }
}
