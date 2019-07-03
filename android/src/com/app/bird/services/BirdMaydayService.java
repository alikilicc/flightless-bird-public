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

import java.util.Locale;

import static com.app.bird.util.Constans.LAST_OPENED_APP;

public class BirdMaydayService extends BroadcastReceiver {

    String TAG = "BirdMaydayService";
    SharedPreferences sharedPreferences;
    int ONE_DAY_MILLISECONDS = 60 * 60 * 24;
    SharedPreferences sharedPref;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (checkIfDailyOpenedApp(context)) {
            if (Locale.getDefault().getLanguage().contains("tr")) {
                sendNotification("Kuş onu kurtarmanı bekliyor...", context);
            } else {
                sendNotification("Bird is waiting your help...", context);
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
        notificationManager.notify(100102, notificationBuilder.build());
    }

}