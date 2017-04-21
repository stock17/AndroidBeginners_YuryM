package com.example.yury.myflashcardsproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        TextView tvQuestion = (TextView) findViewById(R.id.tv_detail_question);
        TextView tvAnswer = (TextView) findViewById(R.id.tv_detail_answer);

        Intent intent = getIntent();
        tvQuestion.setText(intent.getStringExtra("question"));
        tvAnswer.setText(intent.getStringExtra("answer"));

    }
}
