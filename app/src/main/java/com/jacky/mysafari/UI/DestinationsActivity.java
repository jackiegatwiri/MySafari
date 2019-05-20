package com.jacky.mysafari.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jacky.mysafari.Models.Destination;
import com.jacky.mysafari.R;
import com.jacky.mysafari.Services.TriposoService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DestinationsActivity extends AppCompatActivity {

    @BindView(R.id.rView)
    RecyclerView mrView;

    public static final String TAG = DestinationsActivity.class.getSimpleName();
    public ArrayList<Destination> mDestinations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setContentView(R.layout.activity_destinations);
        getDestinations(getIntent().getStringExtra("country"));

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
                    if (response.isSuccessful()){
                    Log.v(TAG, jsonData);
                    mDestinations = triposoService.processResults(response);
                    DestinationsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {



                        }
                    });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
