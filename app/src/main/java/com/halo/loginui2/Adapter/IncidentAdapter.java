package com.halo.loginui2.Adapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.halo.loginui2.Model.Incident;
import com.halo.loginui2.R;
import java.util.ArrayList;
import android.app.Activity;


public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.ViewHolder> {
    private Activity listContext;
    private ArrayList<Incident> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView type;
        public TextView datePublication;
        public TextView comment;
        public TextView state;


        public ViewHolder(CardView v) {
            super(v);
            type = (TextView) v.findViewById(R.id.type);
            datePublication = (TextView) v.findViewById(R.id.datePublication);
            comment = (TextView) v.findViewById(R.id.comment);
            state = (TextView) v.findViewById(R.id.state);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public IncidentAdapter(ArrayList<Incident> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public IncidentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {


        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_incident, parent, false);


        return new ViewHolder(v);
    }




    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.type.setText(mDataset.get(position).getType());
        holder.datePublication.setText(mDataset.get(position).getDatePublication());
        holder.comment.setText(mDataset.get(position).getComment());
        holder.state.setText(mDataset.get(position).getState());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
