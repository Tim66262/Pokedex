package com.example.brippp.pokedex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.brippp.pokedex.dao.PokemonJsonLoader;
import com.example.brippp.pokedex.model.Pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //Element deklaration
   private int counter;
   private ImageButton btnAbbuchen,btnAbbuchen2;
    Bitmap mIcon_val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: activity_main
        setContentView(R.layout.activity_einzelansicht);

        this.btnAbbuchen = (ImageButton) findViewById(R.id.imgBtnEi);
        PokemonJsonLoader.readJsonFromUrl(this,1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Pokemon pokemon = PokemonJsonLoader.createPokemonFromJson(response);

                //image View
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                //textView
                TextView name = (TextView) findViewById(R.id.name);
                TextView size = (TextView) findViewById(R.id.size);
                TextView weight = (TextView) findViewById(R.id.weight);
                TextView find = (TextView) findViewById(R.id.find);


                name.setText(pokemon.getFrontImage());
                size.setText(Integer.toString(pokemon.getHeight()));
                weight.setText(Integer.toString(pokemon.getWeight()));
                find.setText(Integer.toString(pokemon.getBase_experience()));

            }
        });

    }//OnCreate

    //element_UserName.setText(antwort);
    //element_PWD.setText(antwort2);
    //Anmelde Daten in Variable speichern
        /*
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        */


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
