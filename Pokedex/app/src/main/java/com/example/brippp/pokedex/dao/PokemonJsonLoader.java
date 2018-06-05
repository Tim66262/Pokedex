package com.example.brippp.pokedex.dao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.brippp.pokedex.model.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PokemonJsonLoader {

    private static final String basisUrl = "https://pokeapi.co/api/v2/pokemon/";

    /**
     * Reads a Json File and Convert it to a Java String
     * @param context this in Activity
     * @param id Id of Pokemon
     * @param listener A Response Listener Example:
     *
     *  new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
        Pokemon pokemon = PokemonJsonLoader.createPokemonFromJson(response);


        TextView title = (TextView) findViewById(R.id.textView2);
        title.setText(Integer.toString(pokemon.getId()));

        }
        }
     *
     */
    public static void readJsonFromUrl(Context context, int id, Response.Listener<String> listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, basisUrl + id,  listener, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) { }
        });
        queue.add(stringRequest);
    }

    /**
     * Create a Pokemon Object
     * @param jsonString JSON Object in a String
     * @return Pokemon Object
     */
    public static Pokemon createPokemonFromJson(String jsonString) {
        Pokemon pokemon = new Pokemon();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            pokemon.setId(jsonObject.getInt("id"));
            pokemon.setName(jsonObject.getString("name"));
            pokemon.setBase_experience(jsonObject.getInt("base_experience"));
            //pokemon.setHeight(jsonObject.getInt("height"));
            pokemon.setWeight(jsonObject.getInt("weight"));
            pokemon.setHeight(jsonObject.getInt("height"));
            pokemon.setFrontImage(jsonObject.getJSONObject("sprites").getString("front_default"));
            pokemon.setBackImage(jsonObject.getJSONObject("sprites").getString("back_default"));
            List<String> types = new ArrayList<>();
            for(int i = 0; i < jsonObject.getJSONArray("types").length(); i++){
                types.add(jsonObject.getJSONArray("types").getJSONObject(i).getJSONObject("type").getString("name"));
            }
            pokemon.setTypes(types);
        } catch (JSONException e) {
            pokemon.setName("Not found");
            e.printStackTrace();
        }
        return pokemon;
    }
}
