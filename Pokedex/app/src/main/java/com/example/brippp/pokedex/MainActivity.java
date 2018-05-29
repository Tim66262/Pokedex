package com.example.brippp.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Click auf das Ei
     * @param view
     */
    public void onClickEi(View view) {
        counter += 1;
        if(counter > 5){

        }
        if(counter > 10){

        }
        if(counter > 15){

        }
    }
}
