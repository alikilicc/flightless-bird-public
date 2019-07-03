package com.app.bird;

public interface PlayServices {

     void signIn();
     void signOut();
     void rateGame();
     void showScore();
     void showDutyList();
     void setScoreToLeaderBoard(int score);
     void achievementLevel(int level);
     boolean isSignedIn();

}