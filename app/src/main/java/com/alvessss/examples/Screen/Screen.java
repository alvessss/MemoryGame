package com.alvessss.examples.Screen;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;

import com.alvessss.examples.Game.Game;
import com.alvessss.examples.R;

@SuppressWarnings("all")
public class Screen {
   public static final int TOTAL_SQUARES = 12;
   private final int DEF_COLOR = R.color.square_default_color;
   private final int RIGHT_COLOR = R.color.square_right_selected_color;
   private final int WRONG_COLOR = R.color.square_wrong_selected_color;
   private final int HINT_COLOR = R.color.square_hint_color;

   private Square[] squares = new Square[TOTAL_SQUARES];
   private Integer[] sequenceQueue;
   private int sequenceIndex = 0;

   private boolean showedSequence = false;

   private Game.Callback onClickOnRightSquare;
   private Game.Callback onClickOnWrongSquare;
   private Game.Callback onSequenceDone;

   public Screen(Activity activity) {
      final int[] ID = {
         R.id.square_1, R.id.square_2, R.id.square_3,
         R.id.square_4, R.id.square_5, R.id.square_6,
         R.id.square_7, R.id.square_8, R.id.square_9,
         R.id.square_10, R.id.square_11, R.id.square_12};
         for (int square = 0; square < TOTAL_SQUARES; square++){
            int id = ID[square];
            this.squares[square] = new Square(activity.findViewById(id));
         }
   }

   public void enableOnClickListeners() {
      new Handler().post(new Runnable() {
         @Override
         public void run(){
            if (hasShowedSequence()) {
               for (int square = 0; square < TOTAL_SQUARES; square++) {
                  squares[square].setOnClickListener(new SquareListener(squares[square]));
               }
               setShowedSequence(false);
               return;
            }
            new Handler().post(this);
         }
      });
   }

   public void disableOnClickListeners() {
      for (int square = 0; square < TOTAL_SQUARES; square++) {
         this.squares[square].setOnClickListener(null);
      }
   }

   public void setShowedSequence(boolean bool) {
      this.showedSequence = bool;
   }

   public boolean hasShowedSequence() {
      return this.showedSequence;
   }

   public void setOnClickOnRightSquare(Game.Callback callback) {
      this.onClickOnRightSquare = callback;
   }

   public void setOnClickOnWrongSquare(Game.Callback callback) {
      this.onClickOnWrongSquare = callback;
   }

   public void setOnSequenceDone(Game.Callback callback) {
      this.onSequenceDone = callback;
   }

   public void showSequenceOnScreen() {
      for (int square = 0; square < this.sequenceQueue.length; square++) {
         final int squareFinal = square;
         final int squareLayoutPosition = this.sequenceQueue[square];
         new Handler().postAtTime(()->{
            hintClick(this.squares[squareLayoutPosition].getView(), squareFinal);
         }, SystemClock.uptimeMillis() + ((1+square) * 1100));
      }
   }

   public void setSequenceQueue(Integer[] sequence) {
      this.sequenceIndex = 0;
      this.sequenceQueue = sequence;
      setNextInTheQueue();
   }

   private void setNextInTheQueue() {
      if (this.sequenceIndex == this.sequenceQueue.length) {
         this.onSequenceDone.run(-1);
         return;
      }
      int targetSquare = this.sequenceQueue[this.sequenceIndex++]; // increase SEQUENCE_INDEX
      for (int square = 0; square < TOTAL_SQUARES; square++) {
         if (square == targetSquare) {
            this.squares[targetSquare].setAsNextInTheQueue(true);
            continue;
         }
         this.squares[square].setAsNextInTheQueue(false);
      }
   }

   private void hintClick(View view, int squareIndex) {
      view.setBackgroundColor(view.getResources().getColor(HINT_COLOR));
      Animations.fade(view, -0.1F, 800, ()->{
         view.setBackgroundColor(view.getResources().getColor(DEF_COLOR));
         Animations.fade(view, +0.1F, 400, ()-> {
            if (squareIndex == this.sequenceQueue.length - 1) {
               this.showedSequence = true;
            }
         });
      });
   }

   private void rightClick(View view) {
      this.onClickOnRightSquare.run(this.sequenceIndex);
      setNextInTheQueue();
      view.setBackgroundColor(view.getResources().getColor(RIGHT_COLOR));
      Animations.fade(view, -0.1F, 800, ()-> {
         view.setBackgroundColor(view.getResources().getColor(DEF_COLOR));
         Animations.fade(view, +0.1F, 400);
      });
   }

   private void wrongClick(View view) {
      view.setBackgroundColor(view.getResources().getColor(WRONG_COLOR));
      Animations.fade(view, -0.1F, 800, ()-> {
         view.setBackgroundColor(view.getResources().getColor(DEF_COLOR));
         Animations.fade(view, +0.1F, 400);
      });
      this.onClickOnWrongSquare.run(-1);
   }

   private class SquareListener implements View.OnClickListener {
      Square squareInstance;
      SquareListener(Square squareInstance) {
         this.squareInstance = squareInstance;
      }
      @Override public void onClick(View view){
         if (squareInstance.isNextInTheQueue()) {
            rightClick(view);
            return;
         }
         wrongClick(view);
      }
   }
}