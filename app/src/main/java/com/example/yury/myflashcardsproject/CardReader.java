package com.example.yury.myflashcardsproject;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;


public class CardReader extends AsyncTask <Void, Void, String>{
    Context context;

    public CardReader (Context context) {
        super();
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String jsonString = "";

        try {
            InputStream inputStream = context.getAssets().open(MainActivity.JSON_FILE);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
            
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        return jsonString;
    }

    @Override
    protected void onPostExecute(String jsonString) {
        HashMap<String, String> jsonMap = new HashMap<>();

        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("questions");

            for ( int i = 0; i < jsonArray.length(); i++) {
                jsonMap.put(jsonArray.getJSONObject(i).getString("question"),
                        jsonArray.getJSONObject(i).getString("answer"));
            }

            MainActivity.addCardsFromMap(jsonMap);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
