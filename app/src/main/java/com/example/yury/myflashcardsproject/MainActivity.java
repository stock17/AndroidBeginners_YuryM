package com.example.yury.myflashcardsproject;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.yury.myflashcardsproject.card.CardLoader;
import com.example.yury.myflashcardsproject.database.CardContract;
import com.example.yury.myflashcardsproject.database.DBCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.yury.myflashcardsproject.RVAdapter.context;


public class MainActivity extends AppCompatActivity  {

    public static String JSON_FILE = "questions.json";
    public List<Card> cards;
    public static RVAdapter adapter;
    public static AssetManager am;
    public static Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am = getAssets();
        context = getApplicationContext();

        cards = new ArrayList<>();

        RecyclerView rvCardList = (RecyclerView) findViewById(R.id.rvCardList);
        rvCardList.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvCardList.setLayoutManager(manager);
        adapter = new RVAdapter(cards, this);
        rvCardList.setAdapter(adapter);

        final DialogFragment addCardDialog = new AddCardDialog();

        FloatingActionButton fabAddCard = (FloatingActionButton) findViewById(R.id.fab_add_card);
        fabAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCardDialog.show(getSupportFragmentManager(), "TAG");

            }
        });

        CardLoader cardLoader = new CardLoader();
        cardLoader.execute();

    }

   public static class Card {
        String question;
        String answer;

        public Card(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }
    }



}
