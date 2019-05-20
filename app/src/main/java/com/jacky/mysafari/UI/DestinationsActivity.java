package com.jacky.mysafari.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jacky.mysafari.Adapters.DestinationsAdapter;
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
    DestinationsAdapter destinationAdapter;

    @BindView(R.id.rView)
    RecyclerView mrView;

    public static final String TAG = DestinationsActivity.class.getSimpleName();
    public ArrayList<Destination> mDestinations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);

        ButterKnife.bind(this);


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


                    mDestinations = triposoService.processResults(response);
                    DestinationsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            destinationAdapter = new DestinationsAdapter(mDestinations, getApplicationContext());
                           mrView.setAdapter(destinationAdapter);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                           mrView.setLayoutManager(layoutManager);
                            mrView.setHasFixedSize(true);

                        }
                    });
            }
        });
    }
}
