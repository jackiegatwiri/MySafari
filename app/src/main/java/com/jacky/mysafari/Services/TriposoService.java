package com.jacky.mysafari.Services;
import android.util.Log;

import com.jacky.mysafari.Constants;
import com.jacky.mysafari.Models.Country;
import com.jacky.mysafari.Models.Destination;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TriposoService {

    public ArrayList<Destination> processResults(Response response){
        ArrayList <Destination> destinations = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            JSONObject triposoJSON = new JSONObject(jsonData);
            JSONArray resultsJSON =  triposoJSON.getJSONArray("results");
            if (response.isSuccessful()){
                for(int i = 0; i < resultsJSON.length(); i++){
                    JSONObject destinationJSON = resultsJSON.getJSONObject(i);
                    String name = destinationJSON.getString("name");
                    String type = destinationJSON.getString("type");
                    String website = destinationJSON.getJSONArray("attribution").getJSONObject(0).getString("url");
                    String snippet = destinationJSON.getString("snippet");
                    double rating = destinationJSON.getDouble("score");
                    double latitude = destinationJSON.getJSONObject("coordinates").getDouble("latitude");
                    double longitude = destinationJSON.getJSONObject("coordinates").getDouble("longitude");
                    String image = destinationJSON.getJSONArray("images").getJSONObject(0).getString("source_url");

                    Destination destination = new Destination(name, type,  snippet, website, rating, image, latitude, longitude);
                        destinations.add(destination);
                    }

                }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinations;

    }

    private static final String TAG = "TriposoService";
    public static void findDestinations(String destination, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TRIPOSO_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("account",Constants.TRIPOSO_ACCOUNT);
        urlBuilder.addQueryParameter("token",Constants.TRIPOSO_TOKEN);
        urlBuilder.addQueryParameter("part_of",destination);
        urlBuilder.addQueryParameter(Constants.COUNT,"100");
        urlBuilder.addQueryParameter("score",">7");
        String url = urlBuilder.build().toString();

        Log.d(TAG, url);
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
         call.enqueue(callback);

    }
}
