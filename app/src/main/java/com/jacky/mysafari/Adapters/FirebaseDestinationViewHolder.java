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

public class FirebaseDestinationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseDestinationViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindDestination(Destination destiation) {
        ImageView destinationImageView = (ImageView) mView.findViewById(
                R.id.DestinationImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.destinationNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.cityTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.get().load(destiation.getmImageUrl()).into(destinationImageView);
        nameTextView.setText(destiation.getmName());
        categoryTextView.setText(destiation.getmType());
        ratingTextView.setText("Rating: " + destiation.getmRating() + "/10");

    }

    @Override
    public void onClick(View v) {
        final ArrayList<Destination> destinations = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DESTINATIONS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    destinations.add(snapshot.getValue(Destination.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, DestinationDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("destinations", Parcels.wrap(destinations));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
