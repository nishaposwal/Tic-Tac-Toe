package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // cross = 0, circle = 1
     int activePlayer = 0;
     int[] gameState = {2,2,2,2,2,2,2,2,2};
     boolean isGameActive = true;
     int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1, 4, 7}, {2, 5, 8}, {0,4,8}, {2, 4, 6}};
    public void crossIn(View view) {
        ImageView cir = (ImageView) view;

//        System.out.println(view.getTag().toString());
        int tapped = Integer.parseInt(view.getTag().toString());
        if (gameState[tapped] == 2 && isGameActive) {
            cir.setTranslationY(-1000f);
            gameState[tapped] = activePlayer;
            if (activePlayer == 0) {
                cir.setBackgroundResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                activePlayer = 0;
                cir.setBackgroundResource(R.drawable.cir);
            }

            cir.animate().translationYBy(1000f).rotationBy(360f).setDuration(500);
        }
        for(int [] winningPosition : winningPositions) {
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]]  &&
                    gameState[winningPosition[1]]  == gameState[winningPosition[2]]  &&
            gameState[winningPosition[0]]  != 2) {
                isGameActive = false;
//                Toast toast=Toast.makeText(getApplicationContext(),"player won: " + gameState[winningPosition[0]] ,Toast.LENGTH_LONG);
//                toast.show();
                String winner = "Cross";
                if(activePlayer == 0)
                    winner = "Circle";
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner + " has Won!!");
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
                linearLayout.setVisibility(View.VISIBLE);
            } else {

                boolean gameIsOver = true;

                for(int i = 0; i < gameState.length; i++) {
                    if(gameState[i] == 2)
                        gameIsOver = false;
                }

                if(gameIsOver)
                {
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText("its a draw");
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    public void playAgain(View view) {

        isGameActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;


        Arrays.fill(gameState, 2);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setBackgroundResource(0);

        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}