package com.example.yury.myflashcardsproject.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
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

/**
 * Created by Yury on 01.05.2017.
 */

public class CardWidget extends AppWidgetProvider {

    private List<MainActivity.Card> widgetList;

    private final String LOG_TAG = "widget";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));
        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }
    }

    private void updateWidget(Context context, AppWidgetManager appWidgetManager, int id) {

        DBCard dbc = new DBCard(context);
        widgetList = dbc.getAllCards();

        int n = widgetList.size();
        RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        if (n < 1) {
            widgetView.setTextViewText(R.id.tv_wigdet, "No questions in database. Please, add cards in application");
        } else {
            Random random = new Random();
            String question = widgetList.get(random.nextInt(n)).question;
            widgetView.setTextViewText(R.id.tv_wigdet, question);
        }

        Intent nextIntent = new Intent(context, CardWidget.class);
        nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, id, nextIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.btn_wdt_next, pIntent);

        appWidgetManager.updateAppWidget(id, widgetView);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        Bundle extras = intent.getExtras();
        if (extras != null) {
            int id = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);
            updateWidget(context, AppWidgetManager.getInstance(context), id );
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }

}
