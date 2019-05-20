package com.jacky.mysafari.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jacky.mysafari.UI.DestinationDetailActivity;
import com.jacky.mysafari.Models.Destination;
import com.jacky.mysafari.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DestinationsAdapter extends RecyclerView.Adapter<DestinationsAdapter.DestinationsViewHolder> {

    private ArrayList<Destination> mDestinations = new ArrayList<>();
    private Context mContext;

    public DestinationsAdapter(ArrayList<Destination> mDestinations, Context mContext) {
        this.mDestinations = mDestinations;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DestinationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination, parent, false);
        return new DestinationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationsAdapter.DestinationsViewHolder holder, int position) {
        holder.bindDestination(mDestinations.get(position));

    }

    @Override
    public int getItemCount() {
        return mDestinations.size();
    }

    public class DestinationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.destination_name)
        TextView Destination;
        @BindView(R.id.imageid)
        ImageView imageView;
        private Context context;

        public DestinationsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        public void bindDestination(Destination destination) {
            Destination.setText(destination.getmName());
            Picasso.get().load(destination.getmImageUrl()).into(imageView);
        }
        @Override
        public void onClick(View v) {
            int itemPosition  = getLayoutPosition();
            Intent intent = new Intent(mContext, DestinationDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("destinations", Parcels.wrap(mDestinations));
            context.startActivity(intent);


        }
    }
}
