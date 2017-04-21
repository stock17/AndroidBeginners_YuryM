package com.example.yury.myflashcardsproject;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String JSON_FILE = "questions.json";
    public static List<Card> cards;
    public static RecyclerView.Adapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cards = new ArrayList<>();

        RecyclerView rvCardList = (RecyclerView) findViewById(R.id.rvCardList);
        rvCardList.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvCardList.setLayoutManager(manager);
        adapter = new RVAdapter(cards);
        rvCardList.setAdapter(adapter);

        CardReader cardReader = new CardReader(this);
        cardReader.execute();
    }

    public static class Card {
        String question;
        String answer;

        public Card(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }
    }

    private class CardReader extends AsyncTask <Void, Void, String>{
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
                e.printStackTrace();
                return  null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
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

                    cards.add(new Card(jsonArray.getJSONObject(i).getString("question"),
                            jsonArray.getJSONObject(i).getString("answer")));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    }

}
