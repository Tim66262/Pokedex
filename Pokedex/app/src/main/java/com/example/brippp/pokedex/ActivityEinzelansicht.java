package com.example.brippp.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Response;
import com.example.brippp.pokedex.dao.PokemonJsonLoader;
import com.example.brippp.pokedex.model.Pokemon;

public class ActivityEinzelansicht extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einzelansicht);
    }//OnCreate

}
