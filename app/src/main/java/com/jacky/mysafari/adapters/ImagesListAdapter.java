package com.jacky.mysafari.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jacky.mysafari.Country;
import com.jacky.mysafari.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesListAdapter extends RecyclerView.Adapter< ImagesListAdapter.ImagesViewHolder>{

    private ArrayList<Country> mCountry = new ArrayList<>();
    private Context mContext;

    public ImagesListAdapter(Context context, ArrayList<Country> countries) {
        mContext = context;
        mCountry = countries;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.images, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        holder.bindCountry(mCountry.get(position));

    }

    @Override
    public int getItemCount() {
        return mCountry.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.country_name) TextView mCountry;

        private Context mContext;
        public ImagesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        public void bindCountry(Country country) {
            mCountry.setText(country.getName());
        }

    }
}
