package com.alvessss.examples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alvessss.examples.Game.Game;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
   private Game memoryGame;

   @Override
   protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }

   @Override
   protected void onStart(){
      super.onStart();
      memoryGame = new Game(this);
   }
}