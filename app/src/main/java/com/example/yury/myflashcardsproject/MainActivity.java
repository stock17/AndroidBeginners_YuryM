package com.example.yury.myflashcardsproject;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yury.myflashcardsproject.card.CardLoader;
import com.example.yury.myflashcardsproject.service.CardSchedulerService;

import java.util.ArrayList;
import java.util.List;


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

        // SCHEDULER PART

        JobScheduler mJobScheduler = (JobScheduler)
                getSystemService( Context.JOB_SCHEDULER_SERVICE );
        JobInfo.Builder builder = new JobInfo.Builder( 1,
                new ComponentName( getPackageName(),
                        CardSchedulerService.class.getName() ) );
        builder.setPeriodic(10000);
        mJobScheduler.schedule(builder.build());

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
