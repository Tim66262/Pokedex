package com.example.brippp.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //Element deklaration
   private int counter;
   private ImageButton btnAbbuchen,btnAbbuchen2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: activity_main
        setContentView(R.layout.activity_einzelansicht);

        this.btnAbbuchen = (ImageButton) findViewById(R.id.imgBtnEi);

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
