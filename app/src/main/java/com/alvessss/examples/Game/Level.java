package com.alvessss.examples.Game;

import android.app.Activity;
import android.widget.TextView;

import com.alvessss.examples.R;

@SuppressWarnings("all")
class Level {
   private final String tvLevelPrefix = "level: ";
   private final String tvScorePrefix = "score: ";
   private final String tvMaxScorePrefix = "max score: ";
   private final TextView tvLevel;
   private final TextView tvScore;
   private final TextView tvMaxScore;

   private int level = 1;
   private int score = 0;
   private int maxScore = 0;
   private int scoreIncrement = 20;

   Level(Activity activity) {
      this.tvLevel = activity.findViewById(R.id.level);
      this.tvScore = activity.findViewById(R.id.score);
      this.tvMaxScore = activity.findViewById(R.id.max_score);
   }

   public void updateOnScreen(){
      tvLevel.setText(tvLevelPrefix + level);
      tvScore.setText(tvScorePrefix + score);
      tvMaxScore.setText(tvMaxScorePrefix + maxScore);
   }

   public void reset() {
      this.level = 1;
      this.score = 0;
   }

   public void setMaxScore(int maxScore) {
      if (this.maxScore < maxScore) {
         this.maxScore = maxScore;
      }
   }

   public int getLevel(){
      return this.level;
   }

   public int getScore(){
      return this.score;
   }

   public int increaseLevel(){
      level++;
      return level;
   }

   public int increaseScore(int multiplier){
      this.score += multiplier * scoreIncrement;
      return score;
   }
}
