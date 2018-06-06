package com.example.brippp.pokedex.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brippp.pokedex.R;
import com.example.brippp.pokedex.db.DBHelper;

import java.util.ArrayList;

public class PokemonAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<String> nameList;


    public PokemonAdapter(Context context, ArrayList<String> nameList) {
        this.context = context;
        this.nameList = nameList;
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    /**
     * Add one Pokemon to a List.
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        //Set a View i no View is in the Param
        if(convertView == null){
            convertView = View.inflate(context, R.layout.items_list, null);
        }

        //Items in the View
        final ImageView img = convertView.findViewById(R.id.imgPokemon);
        final TextView tv = convertView.findViewById(R.id.lblName);
        final ImageView rating = convertView.findViewById(R.id.imgRating);
        final String name = nameList.get(position);

        //Set Name
        String upperTyp = name.substring(0,1).toUpperCase() + name.substring(1);
        tv.setText(upperTyp);

        //Set Image
        String imageName = nameList.get(position).replace('-', '_');
        img.setImageResource(context.getResources().getIdentifier(imageName, "drawable", context.getPackageName()));

        if(img.getDrawable() == null){
            img.setImageResource(R.drawable.unknown);
        }

        //Set Favorite
        if(new DBHelper(context).isFavorite(name)) {
            rating.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else{
            rating.setImageResource(android.R.drawable.btn_star_big_off);
        }

        //Set a Listener
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new DBHelper(context).isFavorite(name)){
                    new DBHelper(context).deleteFavorite(name);
                    rating.setImageResource(android.R.drawable.btn_star_big_off);
                }
                else {
                    new DBHelper(context).insertFavorite(name);
                    rating.setImageResource(android.R.drawable.btn_star_big_on);
                }
            }
        });

        return convertView;
    }
}
