package com.jacky.mysafari.Services;
import android.util.Log;

import com.jacky.mysafari.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class TriposoService {
    private static final String TAG = "TriposoService";
    public static void findDestinations(String destination, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TRIPOSO_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("account",Constants.TRIPOSO_ACCOUNT);
        urlBuilder.addQueryParameter("token",Constants.TRIPOSO_TOKEN);
        urlBuilder.addQueryParameter("part_of",destination);
        String url = urlBuilder.build().toString();

        Log.d(TAG, url);
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
         call.enqueue(callback);

    }
}
