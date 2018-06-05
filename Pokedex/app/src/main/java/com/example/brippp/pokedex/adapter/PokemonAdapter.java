package com.example.brippp.pokedex.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brippp.pokedex.R;

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

        ImageView img = (ImageView) convertView.findViewById(R.id.imgPokemon);
        TextView tv = (TextView) convertView.findViewById(R.id.lblName);

       try {
           String imageName = nameList.get(position).replace('-', '_');
           img.setImageResource(context.getResources().getIdentifier(imageName, "drawable", context.getPackageName()));
        }
        catch (Exception e) {
           img.setImageResource(R.drawable.unknown);
        }
        tv.setText(nameList.get(position));

        return convertView;
    }
}
