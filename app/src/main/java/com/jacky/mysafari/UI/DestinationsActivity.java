package com.jacky.mysafari.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.jacky.mysafari.R;
import com.jacky.mysafari.Services.TriposoService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DestinationsActivity extends AppCompatActivity {

    public static final String TAG = DestinationsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);


        getDestinations("France");

    }

    private void getDestinations(String destination){
        final TriposoService triposoService = new TriposoService();
        triposoService.findDestinations(destination, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String jsonData = response.body().string();
                    Log.d(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
