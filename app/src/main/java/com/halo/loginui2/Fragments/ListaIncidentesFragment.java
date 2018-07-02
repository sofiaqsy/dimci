package com.halo.loginui2.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.halo.loginui2.Adapter.IncidentAdapter;
import com.halo.loginui2.Model.Incident;
import com.halo.loginui2.R;
import com.halo.loginui2.Decoration.SimpleDividerItemDecoration;
import com.halo.loginui2.service.IncidentService;


import java.util.ArrayList;

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

        FloatingActionButton NewIncident = (FloatingActionButton) view.findViewById(R.id.new_incident);

        NewIncident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),  com.halo.loginui2.vistas.NewIncident.class);
                startActivity(intent);
            }
        });

        return view;

    }

}
