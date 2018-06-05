package com.example.brippp.pokedex.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.items_list, null);
        }

        ImageView img = convertView.findViewById(R.id.imgPokemon);
        TextView tv = convertView.findViewById(R.id.lblName);
        final ImageView rating = convertView.findViewById(R.id.imgRating);

        String imageName = nameList.get(position).replace('-', '_');
        img.setImageResource(context.getResources().getIdentifier(imageName, "drawable", context.getPackageName()));

        final String name = nameList.get(position);
        String upperTyp = name.substring(0,1).toUpperCase() + name.substring(1);
        tv.setText(upperTyp);

        if(new DBHelper(context).isFavorite(name)) {
            rating.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else{
            rating.setImageResource(android.R.drawable.btn_star_big_off);
        }

        if (img.getDrawable() == null) {
            img.setImageResource(R.drawable.unknown);
        }

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DBHelper(context).insertFavorite(name);
                rating.setImageResource(android.R.drawable.btn_star_big_on);
            }
        });

        return convertView;
    }
}
