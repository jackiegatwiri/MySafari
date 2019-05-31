package com.jacky.mysafari.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.jacky.mysafari.Models.Destination;
import com.jacky.mysafari.R;
import com.jacky.mysafari.util.ItemTouchHelperAdapter;
import com.jacky.mysafari.util.OnStartDragListener;

public class FirebaseDestinationListAdapter extends FirebaseRecyclerAdapter<Destination, FirebaseDestinationViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseDestinationListAdapter(FirebaseRecyclerOptions<Destination> options,
                                         DatabaseReference ref,
                                         OnStartDragListener onStartDragListener,
                                         Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }




    @NonNull
    @Override
    public FirebaseDestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_list_item_drag, parent, false);
        return new FirebaseDestinationViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){

        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position){
getRef(position).removeValue();
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseDestinationViewHolder viewHolder, int position, @NonNull Destination model) {
        viewHolder.bindDestination(model);
        viewHolder.mDestinationImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

}
