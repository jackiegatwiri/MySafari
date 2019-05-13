package com.jacky.mysafari.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.jacky.mysafari.Country;

import java.util.ArrayList;

public class imagesListAdapter extends RecyclerView.Adapter< imagesListAdapter.ImagesViewHolder>{

    private ArrayList<Country> mCountry = new ArrayList<>();
    private Context mContext;

    public imagesListAdapter(Context context, ArrayList<Country> countries) {
        mContext = context;
        mCountry = countries;
    }
}
