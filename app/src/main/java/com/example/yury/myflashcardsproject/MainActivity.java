package com.example.yury.myflashcardsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

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
