package com.example.brippp.pokedex.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllPokemonJsonLoader {
    private static final String basisUrl = "https://pokeapi.co/api/v2/pokemon/?limit=1000";

    public static void readJsonFromUrl(Context context, Response.Listener<String> listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, basisUrl,  listener, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
                Log.v("response", "error");
            }
        });
        queue.add(stringRequest);
    }

    public static ArrayList<String> getAllPokemons(String jsonString){
        ArrayList<String> nameList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            for (int i = 0; i < (jsonObject.getJSONArray("results").length()); i++) {
                nameList.add(jsonObject.getJSONArray("results").getJSONObject(i).getString("name"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return nameList;
    }
}
