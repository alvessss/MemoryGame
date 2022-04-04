package com.alvessss.examples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alvessss.examples.Game.MemoryGame;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
   private MemoryGame memoryGame;

   @Override
   protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }

   @Override
   protected void onStart(){
      super.onStart();
      memoryGame = new MemoryGame(this);
   }
}