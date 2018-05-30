package com.example.brippp.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.brippp.pokedex.model.Pokemon;
import com.example.brippp.pokedex.dao.PokemonJsonLoader;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Element deklaration
   private int counter;
   private ImageButton btnAbbuchen,btnAbbuchen2;
   private TextView willkommen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PokemonJsonLoader.readJsonFromUrl(this,1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Pokemon pokemon = PokemonJsonLoader.createPokemonFromJson(response);


                TextView title = (TextView) findViewById(R.id.textView2);
                title.setText(Integer.toString(pokemon.getId()));

            }
        });
        this.btnAbbuchen = (ImageButton) findViewById(R.id.imgBtnEi);

    }


    public void onClickEi(final View view) {
        counter += 1;
        if(counter >= 5){
            btnAbbuchen.setImageResource(R.drawable.egg1);
        }
        if(counter >= 10){
            btnAbbuchen.setImageResource(R.drawable.egg2);
        }
        if(counter >= 15){
            btnAbbuchen.setImageResource(R.drawable.glurak);
            //TODO: grösserer Glurak
            //this.btnAbbuchen2 = (ImageButton) findViewById(R.id.imgBtnGlurak);
            //Ei auf nicht Sichtbar
            //btnAbbuchen.setVisibility(View.GONE);
            //Glurak auf Sichtbar
            //btnAbbuchen2.setVisibility(View.VISIBLE);


        }
    }
    //Onlick Button
    public void onClickStart(View view) {
        Intent i = new Intent(MainActivity.this, ActivityListe.class);
        startActivity(i);
    }
}
