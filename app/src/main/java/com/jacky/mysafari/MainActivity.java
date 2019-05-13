package com.jacky.mysafari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jacky.mysafari.adapters.ImagesListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    private TextView mnameView;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ImagesListAdapter mAdapter;

    public ArrayList<Country> countries = new ArrayList<>(Arrays.asList(
            new Country("England"),
            new Country("Italy"),
            new Country("Jamaica"),
            new Country("Korea"),
            new Country("France"),
            new Country("Mexico")
    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnameView = (TextView) findViewById(R.id.userView);
        Intent intent = getIntent();
        String emails = intent.getStringExtra("emails");
        mnameView.setText("You have successfully logged in: " + emails);

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new ImagesListAdapter(getApplicationContext(), countries);
        mRecyclerView.setAdapter(mAdapter);
//        RecyclerView.LayoutManager layoutManager =
//                new LinearLayoutManager(MainActivity.this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
}
