package com.app.bird.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.app.bird.services.BirdMaydayService;
import com.app.bird.services.LevelReminderService;

import java.util.Calendar;

public class ReminderManager {
    private static ReminderManager ourInstance;
    String TAG = "ReminderManager";

    public static ReminderManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new ReminderManager();
        }
        return ourInstance;
    }

    private static final int LAST_LEVEL_SERVICE = 100101;
    private static final int MAYDAY_SERVICE_ID = 100102;

    public void initReminders(Context context) {
        // setReminderBirdMayday(context, 12, 45);
        // setReminderBirdMayday(context, 18, 30);
        Log.d(TAG, "initReminders...");
        setReminderBirdMayday(context, 12, 45);
        setReminderLastLevel(context, 18, 45);
    }


    private void setReminderBirdMayday(Context context, int hour, int min) {
        Intent intent = new Intent(context, BirdMaydayService.class);
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, MAYDAY_SERVICE_ID, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    private void setReminderLastLevel(Context context, int hour, int min) {
        Intent intent = new Intent(context, LevelReminderService.class);
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, LAST_LEVEL_SERVICE, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }


}
