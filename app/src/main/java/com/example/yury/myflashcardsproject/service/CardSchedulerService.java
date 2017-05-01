package com.example.yury.myflashcardsproject.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.v4.app.NotificationCompat;

import com.example.yury.myflashcardsproject.R;


public class CardSchedulerService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        int notificationId = 001;
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Study your cards")
            .setContentText("You must open your FlashCardProject")
            .setAutoCancel(true)
            .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(notificationId, notification);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
