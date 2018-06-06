package com.example.brippp.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.brippp.pokedex.R;
import com.example.brippp.pokedex.adapter.PokemonAdapter;
import com.example.brippp.pokedex.db.DBHelper;

import java.util.ArrayList;

public class ActivityFavoriten extends AppCompatActivity {

    PokemonAdapter pokemonAdapter;
    ArrayList<String> favPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriten);
        ListView listView = findViewById(R.id.listView);
        favPokemons = new DBHelper(this.getApplicationContext()).readFavorites();
        pokemonAdapter = new PokemonAdapter(ActivityFavoriten.this, favPokemons);
        listView.setAdapter(pokemonAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ActivityFavoriten.this, ActivityEinzelansicht.class);
                        intent.putExtra("name", favPokemons.get(position));
                        startActivity(intent);
                    }
                }
        );
    }

    //Onlick Liste
    public void onClickopenListe(View view) {
        Intent i = new Intent(this, ActivityListe.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    //Onlick Favs
    public void onClickopenFavoriten(View view) {
        Toast.makeText(this,"You are already looking at the Favorites",Toast.LENGTH_LONG).show();
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
