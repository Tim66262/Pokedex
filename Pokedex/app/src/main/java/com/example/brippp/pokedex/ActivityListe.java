package com.example.brippp.pokedex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.brippp.pokedex.adapter.PokemonAdapter;
import com.example.brippp.pokedex.dao.AllPokemonJsonLoader;
import com.example.brippp.pokedex.dao.PokemonJsonLoader;
import com.example.brippp.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActivityListe extends AppCompatActivity{

    Context context= this;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListView listView;
    private SearchView searchView;
    ArrayList<String> nameList;
    PokemonAdapter pokemonAdapter;
    String pokeonDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        loadData();
        pokemonAdapter = new PokemonAdapter(ActivityListe.this, nameList);
        listView.setAdapter(pokemonAdapter);
        listView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ActivityListe.this, ActivityEinzelansicht.class);
                    intent.putExtra("name", nameList.get(position));
                    startActivity(intent);
                }
            }
        );

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                loadData();
                pokemonAdapter = new PokemonAdapter(ActivityListe.this, nameList);
                listView.setAdapter(pokemonAdapter);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String userInput = query;

                PokemonJsonLoader.readJsonFromUrl(context, query.toLowerCase(),new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pokeonDetail = PokemonJsonLoader.getDetail(response);

                        if(!pokeonDetail.equals("Not found.")){
                            nameList = new ArrayList<>();
                            nameList.add(userInput.toLowerCase());
                            pokemonAdapter = new PokemonAdapter(ActivityListe.this, nameList);
                            listView.setAdapter(pokemonAdapter);
                        }else {

                        }
                    }
                });

                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void loadData(){
        nameList = new ArrayList<>();
        AllPokemonJsonLoader.readJsonFromUrl(this, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   nameList.addAll(AllPokemonJsonLoader.getAllPokemons(response));
                   pokemonAdapter.notifyDataSetChanged();
            }
        });
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
