package com.jacky.mysafari.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.jacky.mysafari.Adapters.DestinitionPagerAdapter;
import com.jacky.mysafari.Models.Destination;
import com.jacky.mysafari.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DestinationDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private DestinitionPagerAdapter adapterViewPager;
    ArrayList<Destination> mDestinations = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_destination_detail);
            ButterKnife.bind(this);

            mDestinations = Parcels.unwrap(getIntent().getParcelableExtra("destinations"));
            int startingPosition = getIntent().getIntExtra("position", 0);

            adapterViewPager = new DestinitionPagerAdapter(getSupportFragmentManager(), mDestinations);
            mViewPager.setAdapter(adapterViewPager);
            mViewPager.setCurrentItem(startingPosition);
        }
    }

