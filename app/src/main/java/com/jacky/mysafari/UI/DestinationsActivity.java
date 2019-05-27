package com.jacky.mysafari.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.jacky.mysafari.Adapters.DestinationsAdapter;
import com.jacky.mysafari.Constants;
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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

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

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_DESTINATION_KEY, null);
        if(mRecentAddress != null){
            getDestinations(mRecentAddress);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getDestinations(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void getDestinations(String destination) {
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
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        mrView.setLayoutManager(layoutManager);
                        mrView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
    private void addToSharedPreferences(String destination) {
        mEditor.putString(Constants.PREFERENCES_DESTINATION_KEY, destination).apply();
    }

}
