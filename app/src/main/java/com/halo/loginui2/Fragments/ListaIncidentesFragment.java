package com.halo.loginui2.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.halo.loginui2.Adapter.IncidentAdapter;
import com.halo.loginui2.R;
import com.halo.loginui2.service.IncidentService;
import com.halo.loginui2.vistas.DetailsIncident;
import com.halo.loginui2.vistas.NewIncident;

//import pe.edu.utp.newsnow.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaIncidentesFragment extends Fragment {

    private IncidentAdapter incidentAdapter;
    public ListaIncidentesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_lista_incidentes, container, false);

        IncidentService incidentAdapter = new IncidentService(view.getContext());
        incidentAdapter.getListIncidents(view);


        //boton para agregar un nuevo incidente
        FloatingActionButton NewIncident = (FloatingActionButton) view.findViewById(R.id.new_incident);
        NewIncident.setOnClickListener((vista) -> {
            //Toast.makeText(this.getContext()," "+this.getContext()+ "|" +this.getActivity(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent( getActivity()  , com.halo.loginui2.vistas.NewIncident.class);
            startActivity(intent);
        });
        return view;

    }

}
