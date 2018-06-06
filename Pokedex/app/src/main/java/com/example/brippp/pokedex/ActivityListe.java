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
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.brippp.pokedex.adapter.PokemonAdapter;
import com.example.brippp.pokedex.dao.AllPokemonJsonLoader;
import com.example.brippp.pokedex.dao.PokemonJsonLoader;
import com.example.brippp.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActivityListe extends AppCompatActivity{

    final private Context context = this;

    private ListView listView;
    private SearchView searchView;

    private PokemonAdapter pokemonAdapter;

    private ArrayList<String> nameList;
    private boolean loadSuccess = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        //Set the Elements to the Variables
        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);

        //Load the Pokemons in the nameList Variable
        loadData();

        //Generate a new adapter and fill all Pokemons in the
        pokemonAdapter = new PokemonAdapter(ActivityListe.this, nameList);
        listView.setAdapter(pokemonAdapter);

        //Set a Listener on each Pokemon. By an click on a Element the ActivityEinzelansicht will open
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

        //Search function
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String userInput = query;
                //Set the load success to false.
                loadSuccess = false;
                //Load the pokemon by the name in the searchbox
                PokemonJsonLoader.readJsonFromUrlWithError(context, query.toLowerCase(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Get the detail out of the JSON and Check if Pokemon Detail is not found
                        String pokeonDetail = PokemonJsonLoader.getDetail(response);
                        loadSuccess = true;
                        if (!pokeonDetail.equals("Not found.")) {
                            //Load the Pokemon
                            nameList = new ArrayList<>();
                            nameList.add(userInput.toLowerCase());
                            pokemonAdapter = new PokemonAdapter(ActivityListe.this, nameList);
                            listView.setAdapter(pokemonAdapter);
                        }
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Print an Error Message
                        searchView.setQuery("", false);
                        Toast.makeText(context,"No Pokemon Found",Toast.LENGTH_LONG).show();
                        loadData();
                        //Load the list with the Pokemon Adapter
                        pokemonAdapter = new PokemonAdapter(ActivityListe.this, nameList);
                        listView.setAdapter(pokemonAdapter);
                    }
                });
                //Close the Keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //Load all Pokemons by clicken on the x in the close Searchbar
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //Load all Pokemons
                loadData();
                //Load the list with the Pokemon Adapter
                pokemonAdapter = new PokemonAdapter(ActivityListe.this, nameList);
                listView.setAdapter(pokemonAdapter);
                //Close the Keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

    }

    /**
     * Fill the nameList with all Pokemons
     */
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

    /**
     * Click on liste
     * @param view View
     */
    public void onClickopenListe(View view) {
        Toast.makeText(this,"You are already looking at the List",Toast.LENGTH_LONG).show();
    }

    /**
     * Switch on the favorite View
     * @param view View
     */
    public void onClickopenFavoriten(View view) {
        Intent i = new Intent(this, ActivityFavoriten.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
