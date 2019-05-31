package com.jacky.mysafari.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jacky.mysafari.Constants;
import com.jacky.mysafari.Models.Destination;
import com.jacky.mysafari.R;
import com.jacky.mysafari.UI.DestinationDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseDestinationViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    View mView;
    Context mContext;
    public ImageView mDestinationImageView;

    public FirebaseDestinationViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindDestination(Destination destiation) {
        mDestinationImageView = (ImageView) mView.findViewById(R.id.destinationImageView);
        ImageView destinationImageView = (ImageView) mView.findViewById(
                R.id.destinationImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.destinationNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.cityTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.get().load(destiation.getmImageUrl()).into(mDestinationImageView);
        nameTextView.setText(destiation.getmName());
        categoryTextView.setText(destiation.getmType());
        ratingTextView.setText("Rating: " + destiation.getmRating() + "/10");

    }
}
