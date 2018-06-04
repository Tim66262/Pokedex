package com.example.brippp.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityListe extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
    }

    //Onlick Liste
    public void onClickopenListe(View view) {
        Toast.makeText(this,"You are already looking at the List",Toast.LENGTH_LONG).show();
    }
    //Onlick Favs
    public void onClickopenFavoriten(View view) {
        Intent i = new Intent(this, ActivityFavoriten.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
