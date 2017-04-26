package com.example.yury.myflashcardsproject.card;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;


import com.example.yury.myflashcardsproject.MainActivity;
import com.example.yury.myflashcardsproject.database.DBCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.yury.myflashcardsproject.MainActivity.am;
import static com.example.yury.myflashcardsproject.RVAdapter.context;

/**
 * Created by Yury on 25.04.2017.
 */

public class CardLoader extends AsyncTask<Void, Void, List<MainActivity.Card>> {

    @Override
    protected List<MainActivity.Card> doInBackground(Void... voids) {

        String jsonString = "";
        List<MainActivity.Card> cardList   = new ArrayList<>();


//          LOAD CARDS FROM JSON FILE

        try {

            InputStream inputStream = am.open(MainActivity.JSON_FILE);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("questions");
            for ( int i = 0; i < jsonArray.length(); i++) {
                cardList.add(new MainActivity.Card(jsonArray.getJSONObject(i).getString("question"),
                        jsonArray.getJSONObject(i).getString("answer")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

//         LOAD CARDS FROM DATABASE


        DBCard db = new DBCard(MainActivity.context);
        db.open();
        cardList.addAll(db.getAllCards());
        db.close();

        return cardList;
    }

    @Override
    protected void onPostExecute(List<MainActivity.Card> cardList) {
        MainActivity.adapter.update(cardList);
    }
}

