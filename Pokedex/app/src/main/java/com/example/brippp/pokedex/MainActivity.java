package com.example.brippp.pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RectShape;
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

    private int counter;
    private ImageButton btnAbbuchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnAbbuchen = findViewById(R.id.imgBtnEi);
    }

    /**
     * Change the Image of the egg
     * @param view View
     */
    public void onClickEi(View view) {
        counter += 1;
        if (counter >= 5) {
            btnAbbuchen.setImageResource(R.drawable.egg1);
        }
        if (counter >= 10) {
            btnAbbuchen.setImageResource(R.drawable.egg2);
        }
        if (counter >= 15) {
            btnAbbuchen.setImageResource(R.drawable.egg3);
        }
        if (counter >= 20) {
            btnAbbuchen.setImageResource(R.drawable.glurak);
        }
    }

    /**
     * Change the activity by an click on the start Button
     * @param view View
     */
    public void onClickStart(View view) {
        Intent i = new Intent(MainActivity.this, ActivityListe.class);
        startActivity(i);
    }
}
