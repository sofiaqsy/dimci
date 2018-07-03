package com.halo.loginui2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.halo.loginui2.Model.Noticias;
import com.halo.loginui2.Model.Noticias;
import com.halo.loginui2.R;
import com.halo.loginui2.vistas.DetailsNoticias;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.halo.loginui2.service.AppController.TAG;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {
    private Activity listContext;
    private ArrayList<Noticias> mDataset;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView datePublication;
        public TextView mas;
        public ImageView foto;
        LinearLayout linearLayoutNoticias;


        public ViewHolder(CardView v) {
            super(v);
            foto = v.findViewById(R.id.imagen);
            title = (TextView) v.findViewById(R.id.title_noticias);
            datePublication = (TextView) v.findViewById(R.id.datePublication);
            mas = (TextView) v.findViewById(R.id.mas);
            linearLayoutNoticias = v.findViewById(R.id.linearLayoutNoticias);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NoticiasAdapter(ArrayList<Noticias> myDataset , Context context)
    {
        mDataset = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override



    public NoticiasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noticias, parent, false);

        return new ViewHolder(v);
    }




    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(mDataset.get(position).getTitle());

        holder.datePublication.setText(mDataset.get(position).getDatePublication());

        Picasso.get().load(mDataset.get(position).getImagen()).into(holder.foto);


        holder.linearLayoutNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mDataset.get(position).getDatePublication());
                Intent intent = new Intent(mContext, DetailsNoticias.class);
                intent.putExtra("title", mDataset.get(position).getTitle());
                intent.putExtra("description", mDataset.get(position).getDescription());
                intent.putExtra("datePublication", mDataset.get(position).getDatePublication());
                intent.putExtra("imagen", mDataset.get(position).getImagen());

                mContext.startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

