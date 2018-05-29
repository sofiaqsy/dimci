package com.halo.loginui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.halo.loginui2.Fragments.HomeFragment;
import com.halo.loginui2.Fragments.SettingsFragment;
import com.halo.loginui2.Fragments.SourcesFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //switch (item.getItemId()) {
                //case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    //                  return true;
//
                //case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    //  return true;
                //case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    //  return true;
                //}
            //return false;
            return navigateAccordingTo(item.getItemId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateAccordingTo(R.id.navigation_notifications);

    }


    private Fragment getFragmentFor(int id) {
        switch (id) {
            case R.id.navigation_home:
                return new HomeFragment();
            case R.id.navigation_dashboard:
                return new SettingsFragment();
            case R.id.navigation_notifications:
                return new SourcesFragment();
        }
        return null;
    }

    private boolean navigateAccordingTo(int id) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, getFragmentFor(id))
                    .commit();
            return true;
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

}
