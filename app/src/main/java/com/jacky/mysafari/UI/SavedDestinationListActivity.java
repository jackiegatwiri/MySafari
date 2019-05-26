package com.jacky.mysafari.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jacky.mysafari.Adapters.FirebaseDestinationViewHolder;
import com.jacky.mysafari.Constants;
import com.jacky.mysafari.Models.Destination;
import com.jacky.mysafari.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedDestinationListActivity extends AppCompatActivity {
    private DatabaseReference mDestinationReference;
    private FirebaseRecyclerAdapter<Destination, FirebaseDestinationViewHolder> mFirebaseAdapter;
    @BindView(R.id.rView) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);
        ButterKnife.bind(this);

        mDestinationReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DESTINATIONS);
        setUpFirebaseAdapter();

    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Destination> options =
                new FirebaseRecyclerOptions.Builder<Destination>()
                        .setQuery(mDestinationReference, Destination.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Destination, FirebaseDestinationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseDestinationViewHolder firebaseDestinationViewHolder, int i, @NonNull Destination destination) {
                firebaseDestinationViewHolder.bindDestination(destination);
            }

            @NonNull
            @Override
            public FirebaseDestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_destination_detail, parent, false);
                return new FirebaseDestinationViewHolder(view);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }

    }
}
