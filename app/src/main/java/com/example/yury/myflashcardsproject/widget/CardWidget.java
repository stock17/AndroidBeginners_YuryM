package com.example.yury.myflashcardsproject.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.yury.myflashcardsproject.MainActivity;
import com.example.yury.myflashcardsproject.R;
import com.example.yury.myflashcardsproject.card.CardLoader;
import com.example.yury.myflashcardsproject.database.DBCard;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardWidget extends AppWidgetProvider {

    private static final String WIDGET_ANSWER_ACTION = "showansweronthisquestion";
    private static final String WIDGET_NEXTQUESTION_ACTION = "shownextquestion";

    private static final String TEMP_FILE = "temp";
    private static final String SP_QUESTION = "question";
    private static final String SP_ANSWER = "answer";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        super.onUpdate(context, appWidgetManager, appWidgetIds);
        showNextQuestion(context, appWidgetManager, appWidgetIds[0]);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);


        Intent answerIntent = new Intent(context, this.getClass());
        answerIntent.setAction(WIDGET_ANSWER_ACTION);
        PendingIntent answerPendingIntent = PendingIntent.getBroadcast(context, 0, answerIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_wdt_aq, answerPendingIntent);

        Intent nextQuestionIntent = new Intent(context, this.getClass());
        nextQuestionIntent.setAction(WIDGET_NEXTQUESTION_ACTION);
        PendingIntent nextQuestionPendingIntent = PendingIntent.getBroadcast(context, 0, nextQuestionIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_wdt_next, nextQuestionPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    private void showNextQuestion(Context context, AppWidgetManager appWidgetManager, int  id) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        DBCard dbCard = new DBCard(context);
        List<MainActivity.Card> list = dbCard.getAllCards();

        SharedPreferences sp = context.getSharedPreferences(TEMP_FILE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (list.size() < 1) {
            remoteViews.setTextViewText(R.id.tv_wigdet, "No card in database");
            editor.putString(SP_QUESTION, "No card in database");
            editor.putString(SP_ANSWER, "No card in database");
            editor.commit();
        } else {
            Random random = new Random();
            int nCard = random.nextInt(list.size());
            String question = list.get(nCard).question;
            remoteViews.setTextViewText(R.id.tv_wigdet, question);

            editor.putString(SP_QUESTION, list.get(nCard).question);
            editor.putString(SP_ANSWER, list.get(nCard).answer);
            editor.commit();
        }

        appWidgetManager.updateAppWidget(id, remoteViews);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName provider = new ComponentName(context, this.getClass());

        if (WIDGET_ANSWER_ACTION.equalsIgnoreCase(intent.getAction())) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            SharedPreferences sp = context.getSharedPreferences(TEMP_FILE, context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            String answer = sp.getString(SP_ANSWER, "No card in database");
            remoteViews.setTextViewText(R.id.tv_wigdet, answer);
            appWidgetManager.updateAppWidget(provider, remoteViews);

        } else if (WIDGET_NEXTQUESTION_ACTION.equalsIgnoreCase(intent.getAction())) {

            this.onUpdate(context, appWidgetManager, appWidgetManager.getAppWidgetIds(provider));
        }
    }
}
