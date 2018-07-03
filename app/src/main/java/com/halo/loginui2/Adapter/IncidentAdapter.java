package com.halo.loginui2.Adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.halo.loginui2.Fragments.ListaIncidentesFragment;
import com.halo.loginui2.Model.Incident;
import com.halo.loginui2.R;
import com.halo.loginui2.vistas.DetailsIncident;

import java.util.ArrayList;
import android.app.Activity;
import android.widget.Toast;


public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.ViewHolder>{
    //private Activity listContext;
    private ArrayList<Incident> dataset;
    final static String TAG = "IncidenteAdapter";
    private Context context;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView type;
        public TextView datePublication;
        public TextView comment;
        public TextView state;
        public TextView place;
        public RelativeLayout layout;


        public ViewHolder(CardView v) {
            super(v);
            type = (TextView) v.findViewById(R.id.type);
            datePublication = (TextView) v.findViewById(R.id.datePublication);
            state = (TextView) v.findViewById(R.id.state);
            place = v.findViewById(R.id.place);
            layout = v.findViewById(R.id.itemIncidente);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public IncidentAdapter(ArrayList<Incident> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
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
        holder.type.setText(dataset.get(position).getType().getName());
        holder.datePublication.setText(dataset.get(position).getDatePublication());
        holder.place.setText(dataset.get(position).getPlace());
        holder.state.setText(dataset.get(position).getState().getName());

        //evento para el detalle de los incidentes en el recyclerView
        holder.layout.setOnClickListener((view)->{
            Log.i(TAG,"se hizo click");
            Intent intent = new Intent(context, DetailsIncident.class);
            intent.putExtra("lugar",dataset.get(position).getPlace());
            intent.putExtra("estado",dataset.get(position).getState().getName());
            intent.putExtra("tipo",dataset.get(position).getType().getName());
            intent.putExtra("descripcion",dataset.get(position).getComment());
            intent.putExtra("fecha",dataset.get(position).getDatePublication());
            intent.putExtra("imagen",dataset.get(position).getImagen());
            context.startActivity(intent);
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }

}
