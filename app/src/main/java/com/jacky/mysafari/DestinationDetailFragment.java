package com.jacky.mysafari;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jacky.mysafari.Models.Destination;
import com.jacky.mysafari.UI.DestinationsActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DestinationDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.coordinatesButton)
    Button coordinates;
    @BindView(R.id.DestinationImageView)
    ImageView destinationImage;
    @BindView(R.id.destinationNameTextView)
    TextView destinitionText;
    @BindView(R.id.ratingTextView)
    TextView rating;
    @BindView(R.id.cityTextView)
    TextView cityText;
    @BindView(R.id.websiteTextView)
    TextView websiteText;
    @BindView(R.id.urlTextView)
    TextView urlText;
    @BindView(R.id.snippetTextView)
    TextView snippetText;
    @BindView(R.id.saveDestinationButton) Button mSave;


    private Destination mDestinition;
    private Context context;


    public static DestinationDetailFragment newInstance(Destination destination) {
        DestinationDetailFragment destinationDetailFragment = new DestinationDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("destination", Parcels.wrap(destination));
        destinationDetailFragment.setArguments(args);
        return destinationDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDestinition = Parcels.unwrap(getArguments().getParcelable("destination"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destination_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mDestinition.getmImageUrl()).into(destinationImage);
        destinitionText.setText(mDestinition.getmName());
        rating.setText(Double.toString(mDestinition.getmRating()) + "/10");
        cityText.setText(mDestinition.getmType());
        urlText.setText(mDestinition.getmWebsite());
        snippetText.setText(mDestinition.getmSnippet());

        urlText.setOnClickListener(this);
        coordinates.setOnClickListener(this);
        mSave.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {

        if (v == urlText){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mDestinition.getmWebsite()));
            startActivity(webIntent);
        }

        if(v == coordinates){

            String geoUri = "http://maps.google.com/maps?q=loc:" + mDestinition.getmLatitude() + "," + mDestinition.getmLongitude();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri ));
            startActivity(intent);
        }
        if(v == mSave){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference destinationRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DESTINATIONS).child(uid);
            DatabaseReference pushRef = destinationRef.push();
            String pushId = pushRef.getKey();
            mDestinition.setPushId(pushId);
            pushRef.setValue(mDestinition);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();


        }

    }
}
