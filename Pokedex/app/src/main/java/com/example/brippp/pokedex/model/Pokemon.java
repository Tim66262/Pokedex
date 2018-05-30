package com.example.brippp.pokedex.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

public class Pokemon {

    public static final String basisUrl = "http://pokeapi.co/api/v2/pokemon/";

    private int id;
    private String name;
    private int base_experience;
    private int height;
    private int weight;
    private String frontImage;
    private String backImage;
    private List<String> types = new ArrayList<>();

    public Pokemon(String pokemonName){
        JSONObject pokemonJson = readJsonFromUrl(basisUrl + pokemonName + "/");
        if(pokemonJson != null) {
            fillAttributes(pokemonJson);
        }
    }

    public Pokemon(int id){
        JSONObject pokemonJson = readJsonFromUrl(basisUrl + Integer.toString(id) + "/");
        if(pokemonJson != null) {
            fillAttributes(pokemonJson);
        }
    }

    /**
     * Convert the url to a json
     * @param url api which return a json
     * @return jsonObject
     */
    private JSONObject readJsonFromUrl(String url){
        try {
            InputStream is; is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            is.close();
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read the Pokemon out of the API as Text
     * @param rd Reader Object
     * @return Pokemon String
     * @throws IOException
     */
    private String readAll(Reader rd) throws IOException{
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     *
     * @param pokemonJson
     */
    private void fillAttributes(JSONObject pokemonJson) {
        try{
            setName(pokemonJson.getString("name"));
        }catch (JSONException e){

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
