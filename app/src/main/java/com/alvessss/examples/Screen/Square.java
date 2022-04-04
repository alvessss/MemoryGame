package com.alvessss.examples.Screen;

import android.view.View;

import androidx.cardview.widget.CardView;

@SuppressWarnings("all")
class Square {
   private final CardView squareView;

   private boolean nextInTheQueue = false;

   Square(CardView squareView) {
      this.squareView = squareView;
   }

   public CardView getView() {
      return this.squareView;
   }

   public void setOnClickListener(View.OnClickListener onClickListener) {
      this.squareView.setOnClickListener(onClickListener);
   }

   public boolean isNextInTheQueue() {
      return nextInTheQueue;
   }

   public void setAsNextInTheQueue(boolean bool) {
      this.nextInTheQueue = bool;
   }
}