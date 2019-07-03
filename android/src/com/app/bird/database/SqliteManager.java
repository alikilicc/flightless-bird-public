package com.app.bird.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.bird.model.ScoreItem;

import java.util.ArrayList;

public class SqliteManager extends SQLiteOpenHelper {
    private static final String databaseName = "levels";
    private static final int databaseVersion = 1;
    private static final String tableLevels = "tableLevels";
    private String levelScore = "levelScore";
    private String level = "level";
    private String isSuccesfully = "isSuccesfully";

    String CREATE_TABLE_CHANNELS = "CREATE TABLE " + tableLevels +
            " ( " + levelScore + " INTEGER , " + level + " INTEGER , " + isSuccesfully + " TEXT )";

    public SqliteManager(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CHANNELS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + tableLevels);
    }

    public void addItemToScores(ArrayList<ScoreItem> scores) {

        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            for (ScoreItem scoreItem : scores) {
                ContentValues cv = new ContentValues();
                cv.put(levelScore, scoreItem.getScore());
                cv.put(level, scoreItem.getLevel());
                cv.put(isSuccesfully, String.valueOf(scoreItem.getSuccesfully()));
                db.insert(tableLevels, null, cv);
            }
        }


    }

    public ArrayList<ScoreItem> takeChannels() {
        ArrayList<ScoreItem> scoreItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null) {
            String sqlQuery = "SELECT  * FROM " + tableLevels;
            Cursor cursor = db.rawQuery(sqlQuery, null);

            int levelScoreStringIX = cursor.getColumnIndex(levelScore);
            int isSuccessfullIX = cursor.getColumnIndex(isSuccesfully);
            int levelIX = cursor.getColumnIndex(level);


            cursor.moveToFirst();

            if (cursor.moveToFirst()) {

                do {
                    ScoreItem scoreItem = new ScoreItem();
                    scoreItem.setSuccesfully(cursor.getString(isSuccessfullIX).equals("true") ? true : false);
                    scoreItem.setScore(cursor.getInt(levelScoreStringIX));
                    scoreItem.setLevel(cursor.getInt(levelIX));
                    scoreItems.add(scoreItem);
                } while (cursor.moveToNext());

            }
            cursor.close();

        }

        return scoreItems;
    }

    public int reachedLastLevel() {
        ArrayList<ScoreItem> scoreItems = takeChannels();
        int reachedLastLevel = 1;
        for (int index = 0; index < scoreItems.size(); index++) {
            if (index != 0) {
                if (!scoreItems.get(index).getSuccesfully()) {
                    reachedLastLevel = scoreItems.get(index).getLevel();
                    return reachedLastLevel;
                }
            }
        }
        return reachedLastLevel;
    }

    public void clearSavedChannels() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableLevels, null, null);
    }

    public void updateScore(ScoreItem scoreItem) {
     /*   SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(levelScore, scoreItem.getScore());
        cv.put(level, scoreItem.getLevel());
        cv.put(isSuccesfully, String.valueOf(scoreItem.getSuccesfully()));
        db.update(tableLevels, cv, level + " = ? " + new String[]{String.valueOf(scoreItem.getLevel())}, null);*/
    }
}
