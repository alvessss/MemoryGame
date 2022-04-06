package com.alvessss.examples.Game;

import android.app.Activity;
import android.widget.Button;

import com.alvessss.examples.R;
import com.alvessss.examples.Screen.Screen;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("all")
public class Game {
   private final int SEQUENCY_BOUND = 12;
   private final int START_SEQUENCY_SIZE = 3;

   private Screen gameScreen;
   private Level gameLevel;

   private Button startGame;

   private Callback onClickOnRight = squareIndex -> {
      gameLevel.increaseScore(squareIndex);
      gameLevel.updateOnScreen();
   };
   private Callback onClickOnWrong = squareIndex -> {
      gameLevel.setMaxScore(gameLevel.getScore());
      gameLevel.reset();
      gameLevel.updateOnScreen();
      gameScreen.disableOnClickListeners();
   };
   private Callback onSequencyDone = squareIndex -> {
      gameLevel.increaseLevel();
      gameLevel.updateOnScreen();
      gameScreen.disableOnClickListeners();

      gameScreen.setSequenceQueue(getSequence(START_SEQUENCY_SIZE + gameLevel.getLevel() - 1,
         SEQUENCY_BOUND));

      gameScreen.showSequenceOnScreen();
      gameScreen.enableOnClickListeners();
   };

   public Game(Activity activity) {
      startGame = activity.findViewById(R.id.start_game);
      gameLevel = new Level(activity);
      gameScreen = new Screen(activity);

      gameScreen.setOnClickOnRightSquare(onClickOnRight);
      gameScreen.setOnClickOnWrongSquare(onClickOnWrong);
      gameScreen.setOnSequenceDone(onSequencyDone);

      startGame.setOnClickListener(view -> {
         gameScreen.setSequenceQueue(getSequence(START_SEQUENCY_SIZE, SEQUENCY_BOUND));
         gameScreen.showSequenceOnScreen();
         gameScreen.enableOnClickListeners();
      });
   }

   private Integer[] getSequence(int size, int randomBound) {
      ArrayList<Integer> sequence = new ArrayList<>();
      Random random = new Random();
      for (int i = 0; i < size; i++) {
         sequence.add(random.nextInt(randomBound));
         if (i > 0) {
            while (sequence.get(i) == sequence.get(i - 1)) {
               sequence.set(i, random.nextInt(randomBound));
            }
         }
      }
      return sequence.toArray(new Integer[size]);
   }

   public interface Callback {
      void run(int squareIndex);
   }
}