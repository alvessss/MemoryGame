package com.alvessss.examples.Screen;

import android.os.Handler;
import android.view.View;

@SuppressWarnings("unused")
public class Animations {
   public static void fade(final View view, float increment, int delay) {
      fade(view, increment, delay, null);
   }

   public static void fade(final View view, float increment, int delay, Runnable callback) {
      final float minAlpha = 0.0F;
      final float maxAlpha = 1.0F;
      final float reset = increment < 0 ? minAlpha : maxAlpha;
      final int step = delay / (int)(maxAlpha / increment);
      final int delayStep = step < 0 ? -step : step;

      Handler tdHandler = new Handler();
      tdHandler.post(new Runnable() {
         @Override public void run() {
            fade();
         }

         private void fade () {
            float currAlpha = view.getAlpha();
            view.setAlpha(currAlpha += increment);
            if (currAlpha > minAlpha && currAlpha < maxAlpha) {
               tdHandler.postDelayed(this, delayStep);
               return;
            }

            view.setAlpha(reset);
            if (callback != null) {
               tdHandler.post(callback);
            }
         }
      });
   }
}