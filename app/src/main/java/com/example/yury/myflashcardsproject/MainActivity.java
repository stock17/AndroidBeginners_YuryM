package com.example.yury.myflashcardsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String JSON_FILE = "questions.json";

    static Map<String, String> cards = new HashMap<>();

    public static void addCardsFromMap(HashMap<String, String> map) {

        for(Map.Entry<String, String> entry : map.entrySet()){
            cards.put(entry.getKey(), entry.getValue());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvCardList = (RecyclerView) findViewById(R.id.rvCardList);
        rvCardList.setHasFixedSize(true);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvCardList.setLayoutManager(manager);
        RecyclerView.Adapter adapter = new RVAdapter();
        rvCardList.setAdapter(adapter);


    }
}
