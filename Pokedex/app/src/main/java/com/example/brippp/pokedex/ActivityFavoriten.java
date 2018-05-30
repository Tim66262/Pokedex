package com.example.brippp.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.brippp.pokedex.R;

public class ActivityFavoriten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriten);

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
