package com.example.brippp.pokedex;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Response;
import com.example.brippp.pokedex.dao.PokemonJsonLoader;
import com.example.brippp.pokedex.db.DBHelper;
import com.example.brippp.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

public class ActivityEinzelansicht extends AppCompatActivity{

    Pokemon pokemon;
    boolean pictureFront = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einzelansicht);

        //Get the Id of the the Selected Pokemon
        String id;
        if(getIntent().hasExtra("name")){
            id = getIntent().getStringExtra("name");
        }
        else{
            id = "altaria";
        }

        //Load the Pokemondata by Json
        PokemonJsonLoader.readJsonFromUrl(this, id, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                pokemon = PokemonJsonLoader.createPokemonFromJson(response);
                //image View
                final ImageView imageView = findViewById(R.id.imageView);
                final ImageView favorite = findViewById(R.id.imgRating);
                //textView
                final TextView name = findViewById(R.id.name);
                final TextView size = findViewById(R.id.size);
                final TextView weight = findViewById(R.id.weight);
                final TextView find = findViewById(R.id.find);
                final TextView id = findViewById(R.id.ID);
                final LinearLayout typesLayout = findViewById(R.id.layoutTypes);

                //upper Case Pokename
                final String pokemonName = pokemon.getName();
                String upperString = pokemonName.substring(0,1).toUpperCase() + pokemonName.substring(1);
                name.setText(upperString);

                //Fill the Data in the TextViews
                size.setText(Integer.toString(pokemon.getHeight()));
                weight.setText(Integer.toString(pokemon.getWeight()));
                find.setText(Integer.toString(pokemon.getBase_experience()));
                id.setText(Integer.toString(pokemon.getId()));

                //Set Favorite
                if(new DBHelper(getApplicationContext()).isFavorite(pokemonName)) {
                    favorite.setImageResource(android.R.drawable.btn_star_big_on);
                }
                else{
                    favorite.setImageResource(android.R.drawable.btn_star_big_off);
                }

                //Set a Listener
                favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(new DBHelper(getApplicationContext()).isFavorite(pokemonName)){
                            new DBHelper(getApplicationContext()).deleteFavorite(pokemonName);
                            favorite.setImageResource(android.R.drawable.btn_star_big_off);
                        }
                        else {
                            new DBHelper(getApplicationContext()).insertFavorite(pokemonName);
                            favorite.setImageResource(android.R.drawable.btn_star_big_on);
                        }
                    }
                });

                //Set the Pokemonstypes and fill this in the linear layout
                for (String typ: pokemon.getTypes()) {
                    TextView textView = new TextView(getApplicationContext());
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    llp.setMargins(20, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
                    textView.setLayoutParams(llp);
                    //Typ Uppercase
                    String upperTyp = typ.substring(0,1).toUpperCase() + typ.substring(1);
                    textView.setText(upperTyp);
                    textView.setTextSize(18);


                    textView.setBackgroundResource(R.drawable.rounded_button);
                    Drawable background = textView.getBackground();
                    //textView.getPaint().setColor(Color.BLACK);
                    // Set the border width

                    //textView.getPaint().setStrokeWidth(10f);

                    switch (typ) {
                        case "poison":
                            background.setColorFilter(getResources().getColor(R.color.violet), PorterDuff.Mode.OVERLAY);
                            break;
                        case "grass":
                            //background.setColor(R.color.colorAccent);
                            background.setColorFilter(Color.GREEN, PorterDuff.Mode.OVERLAY );

                            break;
                        case "normal":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(Color.GRAY);
                            background.setColorFilter(Color.GRAY, PorterDuff.Mode.OVERLAY );
                            break;
                        case "fighting":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.colorPrimary));
                            background.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.OVERLAY);
                            break;
                        case "flying":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.flying));
                            background.setColorFilter(getResources().getColor(R.color.flying), PorterDuff.Mode.OVERLAY);
                            break;
                        case "ground":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.ground));
                            background.setColorFilter(getResources().getColor(R.color.ground), PorterDuff.Mode.OVERLAY);
                            break;
                        case "stone":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.stone));
                            background.setColorFilter(getResources().getColor(R.color.stone), PorterDuff.Mode.OVERLAY);
                            break;
                        case "rock":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.stone));
                            background.setColorFilter(getResources().getColor(R.color.stone), PorterDuff.Mode.OVERLAY);
                            break;
                        case "ghost":
                            //background.setColor(R.color.colorAccent);
                           // background.setColor(getResources().getColor(R.color.ghost));
                            background.setColorFilter(getResources().getColor(R.color.ghost), PorterDuff.Mode.OVERLAY);
                            break;
                        case "steel":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.steel));
                            background.setColorFilter(getResources().getColor(R.color.steel), PorterDuff.Mode.OVERLAY);
                            break;
                        case "fire":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.fire));
                            background.setColorFilter(getResources().getColor(R.color.fire), PorterDuff.Mode.OVERLAY);
                            break;
                        case "water":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.water));
                            background.setColorFilter(getResources().getColor(R.color.water), PorterDuff.Mode.OVERLAY);
                            break;
                        case "electric":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.electrik));
                            background.setColorFilter(getResources().getColor(R.color.electrik), PorterDuff.Mode.OVERLAY);
                            break;
                        case "psychic":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.psychic));
                            background.setColorFilter(getResources().getColor(R.color.psychic), PorterDuff.Mode.OVERLAY);
                            break;
                        case "ice":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.ice));
                            background.setColorFilter(getResources().getColor(R.color.ice), PorterDuff.Mode.OVERLAY);
                            break;
                        case "dragon":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.drage));
                            background.setColorFilter(getResources().getColor(R.color.drage), PorterDuff.Mode.OVERLAY);
                            break;
                        case "dark":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.dark));
                            background.setColorFilter(getResources().getColor(R.color.dark), PorterDuff.Mode.OVERLAY);
                            break;
                        case "fairy":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.fee));
                            background.setColorFilter(getResources().getColor(R.color.fee), PorterDuff.Mode.OVERLAY);
                            break;
                        case "unknown":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.grey));
                            background.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.OVERLAY);
                            break;
                        case "shadow":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.shadow));
                            background.setColorFilter(getResources().getColor(R.color.shadow), PorterDuff.Mode.OVERLAY);
                            break;
                        case "bug":
                            //background.setColor(R.color.colorAccent);
                            //background.setColor(getResources().getColor(R.color.bug));
                            background.setColorFilter(getResources().getColor(R.color.bug), PorterDuff.Mode.OVERLAY);
                            break;
                    }

                    typesLayout.addView(textView);
                }

                Picasso.get().load(pokemon.getFrontImage()).into(imageView);

            }
        });
    }//OnCreate

    /**
     * Switch the Pokemon Image
     * @param view
     */
    public void turnPokemon(View view){
        ImageView imageView = findViewById(R.id.imageView);
        if(pictureFront){
            Picasso.get().load(pokemon.getBackImage()).into(imageView);
            pictureFront = false;
        }
        else{
            Picasso.get().load(pokemon.getFrontImage()).into(imageView);
            pictureFront = true;
        }

    }

    /**
     * Button to go back to the list of all Poemons
     * @param view
     */
    public void backtoListe (View view){
        Intent i = new Intent(this, ActivityListe.class);
        startActivity(i);
    }
}
