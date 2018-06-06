package com.example.brippp.pokedex;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Response;
import com.example.brippp.pokedex.dao.PokemonJsonLoader;
import com.example.brippp.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

public class ActivityEinzelansicht extends AppCompatActivity{

    Pokemon pokemon;
    boolean pictureFront = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einzelansicht);

        String id;
        if(getIntent().hasExtra("name")){
            id = getIntent().getStringExtra("name");
        }
        else{
            id = "altaria";
        }

        PokemonJsonLoader.readJsonFromUrl(this, id, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                pokemon = PokemonJsonLoader.createPokemonFromJson(response);
                //image View
                ImageView imageView = findViewById(R.id.imageView);
                //textView
                TextView name = findViewById(R.id.name);
                TextView size = findViewById(R.id.size);
                TextView weight = findViewById(R.id.weight);
                TextView find = findViewById(R.id.find);
                TextView id = findViewById(R.id.ID);

                //upper Case Pokename
                String pokemonName = pokemon.getName();
                String upperString = pokemonName.substring(0,1).toUpperCase() + pokemonName.substring(1);
                name.setText(upperString);

                size.setText(Integer.toString(pokemon.getHeight()));
                weight.setText(Integer.toString(pokemon.getWeight()));
                find.setText(Integer.toString(pokemon.getBase_experience()));
                id.setText(Integer.toString(pokemon.getId()));

                LinearLayout typesLayout = findViewById(R.id.layoutTypes);

                for (String typ: pokemon.getTypes()) {
                    TextView textView = new TextView(getApplicationContext());
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    llp.setMargins(20, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
                    textView.setLayoutParams(llp);
                    //Typ Uppercase
                    String upperTyp = typ.substring(0,1).toUpperCase() + typ.substring(1);
                    textView.setText(upperTyp);
                    textView.setTextSize(18);
                    //textView.getPaint().setColor(Color.BLACK);
                    // Set the border width

                    textView.getPaint().setStrokeWidth(10f);

                    if(typ.equals("poison")){
                        textView.setBackgroundColor(getResources().getColor(R.color.violet));
                    }
                    if(typ.equals("grass")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(Color.GREEN);
                    }
                    if(typ.equals("normal")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(Color.GRAY);
                    }
                    if(typ.equals("fighting")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                    if(typ.equals("flying")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.flying));
                    }
                    if(typ.equals("ground")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.ground));
                    }
                    if(typ.equals("stone")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.stone));
                    }
                    if(typ.equals("rock")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.stone));
                    }
                    if(typ.equals("ghost")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.ghost));
                    }
                    if(typ.equals("steel")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.steel));
                    }
                    if(typ.equals("fire")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.fire));
                    }
                    if(typ.equals("water")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.water));
                    }
                    if(typ.equals("electric")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.electrik));
                    }
                    if(typ.equals("psychic")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.psychic));
                    }
                    if(typ.equals("ice")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.ice));
                    }
                    if(typ.equals("dragon")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.drage));
                    }
                    if(typ.equals("dark")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.dark));
                    }
                    if(typ.equals("fairy")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.fee));
                    }
                    if(typ.equals("unknown")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.grey));
                    }
                    if(typ.equals("shadow")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.shadow));
                    }
                    if(typ.equals("bug")){
                        //textView.setBackgroundColor(R.color.colorAccent);
                        textView.setBackgroundColor(getResources().getColor(R.color.bug));
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
