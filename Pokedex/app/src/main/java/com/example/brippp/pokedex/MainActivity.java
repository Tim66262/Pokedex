package com.example.brippp.pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.brippp.pokedex.dao.PokemonJsonLoader;
import com.example.brippp.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //Element deklaration
    private int counter;
    private ImageButton btnAbbuchen, btnAbbuchen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: activity_main
        setContentView(R.layout.activity_einzelansicht);
        this.btnAbbuchen = (ImageButton) findViewById(R.id.imgBtnEi);
        PokemonJsonLoader.readJsonFromUrl(this, 1, new Response.Listener<String>() {
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

                Picasso.get().load(pokemon.getFrontImage()).into(imageView);

                name.setText(pokemon.getName());
                size.setText(Integer.toString(pokemon.getHeight()));
                weight.setText(Integer.toString(pokemon.getWeight()));
                find.setText(Integer.toString(pokemon.getBase_experience()));

                LinearLayout typesLayout = (LinearLayout) findViewById(R.id.layoutTypes);

                for (String typ: pokemon.getTypes()) {
                    TextView textView = new TextView(getApplicationContext());
                    textView.setText(typ);
                    if(typ.equals("poison")){
                        textView.setBackgroundColor(Color.GREEN);
                    }
                    typesLayout.addView(textView);
                }

                Picasso.get().load(pokemon.getFrontImage()).into(imageView);

            }
        });
    }




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
        if (counter >= 5) {
            btnAbbuchen.setImageResource(R.drawable.egg1);
        }
        if (counter >= 10) {
            btnAbbuchen.setImageResource(R.drawable.egg2);
        }
        if (counter >= 15) {
            btnAbbuchen.setImageResource(R.drawable.glurak);
        }
    }

    //Onlick Button
    public void onClickStart(View view) {
        Intent i = new Intent(MainActivity.this, ActivityListe.class);
        startActivity(i);
    }
        }
