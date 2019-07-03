package com.app.bird.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.app.bird.AndroidLauncher;
import com.app.bird.R;
import com.app.bird.database.SqliteManager;

import java.util.Locale;
import java.util.Random;

import static com.app.bird.util.Constans.LAST_OPENED_APP;

public class LevelReminderService extends BroadcastReceiver {

    String TAG = "LevelReminderService";
    SqliteManager sqliteManager;
    SharedPreferences sharedPref;
    int ONE_DAY_MILLISECONDS = 60 * 60 * 24;

    @Override
    public void onReceive(Context context, Intent intent) {
        int reachedLastLevel = getLastReachedLevel(context);
        if (checkIfDailyOpenedApp(context)) {
            if (Locale.getDefault().getLanguage().contains("tr")) {
                sendNotification(reachedLastLevel + ". leveli geçmeye hazır mısın ?", context);
            } else {
                sendNotification("Are you ready for " + numberToOrdinal(reachedLastLevel) + " level", context);
            }
        }
    }

    private boolean checkIfDailyOpenedApp(Context context) {
        boolean showNotif = false;
        if (sharedPref == null) {
            sharedPref = context.getApplicationContext().getSharedPreferences("info", Context.MODE_PRIVATE);
        }
        long lastOpenedApp = sharedPref.getLong(LAST_OPENED_APP, 0L);
        if (lastOpenedApp != 0L) {
            showNotif = lastOpenedApp + ONE_DAY_MILLISECONDS < System.currentTimeMillis();
        }
        return showNotif;
    }

    private int getLastReachedLevel(Context context) {
        if (sqliteManager == null) {
            sqliteManager = new SqliteManager(context);
        }
        return sqliteManager.reachedLastLevel();
    }

    private String numberToOrdinal(int n) {

        if (n == 0) {
            return String.valueOf(n);
        }
        int j = n % 10,
                k = n % 100;


        if (j == 1 && k != 11) {
            return n + "st";
        }
        if (j == 2 && k != 12) {
            return n + "nd";
        }
        if (j == 3 && k != 13) {
            return n + "rd";
        }
        return n + "th";
    }

    private void sendNotification(String messageBody, Context context) {
        Intent intent = new Intent(context, AndroidLauncher.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = context.getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(100101, notificationBuilder.build());
    }

}