

package com.halo.loginui2.Fragments;


        import android.content.res.Configuration;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;



        import com.halo.loginui2.Model.Noticias;
        import com.halo.loginui2.R;
        import com.halo.loginui2.service.NoticiasService;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView sourcesRecyclerView;
    //SourcesAdapter sourcesAdapter;
    RecyclerView.LayoutManager sourcesLayoutManager;
    List<Noticias> noticias;
    String tag;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        NoticiasService noticiasService= new NoticiasService(view.getContext());
        noticiasService.getListNoticias(view);


        return view;
    }





}
