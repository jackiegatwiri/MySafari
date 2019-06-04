package com.jacky.mysafari.UI;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class DestinationListFragment extends Fragment {

    private DestinationsAdapter destinationAdapter;
    String location = "";
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    public ArrayList<Destination> mDestinations = new ArrayList<>();
    @BindView(R.id.recyclerView)
    RecyclerView mrView;



    public DestinationListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        location=  Constants.LOCATION;




//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        mEditor = mSharedPreferences.edit();
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        location=getArguments().getString("country");
//
        View view = inflater.inflate(R.layout.fragment_destination_list, container, false);
        ButterKnife.bind(this, view);

//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_DESTINATION_KEY, null);
//
//        if (mRecentAddress != null) {
//            getDestinations(mRecentAddress);
//        }



        getDestinations(location);
        return view;
    }

    private void getDestinations(String destination) {
        final TriposoService triposoService = new TriposoService();
        triposoService.findDestinations(destination, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response){


                mDestinations = triposoService.processResults(response);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        destinationAdapter = new DestinationsAdapter(mDestinations, getActivity());
                        mrView.setAdapter(destinationAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mrView.setLayoutManager(layoutManager);
                        mrView.setHasFixedSize(true);

                    }
                });
            }
        });
    }


    @Override
    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
                getDestinations(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
//    private void addToSharedPreferences(String destination) {
//        mEditor.putString(Constants.PREFERENCES_DESTINATION_KEY, destination).apply();
//    }

}
