package com.jacky.mysafari.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.jacky.mysafari.Models.Destination;
import com.jacky.mysafari.R;
import com.jacky.mysafari.UI.DestinationDetailActivity;
import com.jacky.mysafari.util.ItemTouchHelperAdapter;
import com.jacky.mysafari.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseDestinationListAdapter extends FirebaseRecyclerAdapter<Destination, FirebaseDestinationViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Destination> mDestinations = new ArrayList<>();



    public FirebaseDestinationListAdapter(FirebaseRecyclerOptions<Destination> options,
                                         Query ref,
                                         OnStartDragListener onStartDragListener,
                                         Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mDestinations.add(dataSnapshot.getValue(Destination.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    @NonNull
    @Override
    public FirebaseDestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_list_item_drag, parent, false);
        return new FirebaseDestinationViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        Collections.swap(mDestinations, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }
    @Override
    public void stopListening() { super.stopListening(); mRef.removeEventListener(mChildEventListener); }

    @Override
    public void onItemDismiss(int position){
        mDestinations.remove(position);
getRef(position).removeValue();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onBindViewHolder(@NonNull FirebaseDestinationViewHolder viewHolder, int position, @NonNull Destination model) {
        viewHolder.bindDestination(model);
        viewHolder.mDestinationImageView.setOnTouchListener((v, event) -> {
            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                mOnStartDragListener.onStartDrag(viewHolder);
            }
            return false;
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DestinationDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("destinations", Parcels.wrap(mDestinations));
                mContext.startActivity(intent);
            }
        });
    }




    private void setIndexInFirebase() {
        for (Destination destination : mDestinations) {
            int index = mDestinations.indexOf(destination);
            DatabaseReference ref = getRef(index);
            destination.setIndex(Integer.toString(index));
            ref.setValue(destination);
        }
    }
}


