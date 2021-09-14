package com.example.game;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int player = 0;
    boolean active = true;
    String winner;

    public void project(View view) {
        ImageView counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && active) {
            gameState[tappedCounter] = player;
            counter.setTranslationY(-1500);
            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPosition) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    active = false;
                    if (player == 0) {
                        winner = "Red has won";
                    } else {
                        winner = "Yellow has won";
                    }


                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
        }
    }

     public void playAgain(View view)
       {
           Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
           TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
           playAgainButton.setVisibility(View.INVISIBLE);
           winnerTextView.setVisibility(View.INVISIBLE);
           GridLayout gridLayout = findViewById(R.id.gridLayout);

           for(int i=0; i<gridLayout.getChildCount(); i++)
           {
               ImageView counter=(ImageView) gridLayout.getChildAt(i);
               counter.setImageDrawable(null);
           }
           for(int i=0;i< gameState.length;i++)
           {
               gameState[i]= 2;
           }
           player=0;
           active=true;

       }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}