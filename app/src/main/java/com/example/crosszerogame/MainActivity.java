package com.example.crosszerogame;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int player = 0;
    private int unplayed[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int winPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private boolean gameIsOn = true;
    private String winner;
    public void clickedButton(View view) {
        ImageView i = (ImageView) view;
        int tag = Integer.parseInt(i.getTag().toString());
        if (unplayed[tag] == 2 && gameIsOn) {
            unplayed[tag] = player;
            if (player == 0) {
                i.setImageResource(R.drawable.zero);
                player = 1;
            } else {
                i.setImageResource(R.drawable.cross);
                player = 0;
            }
            for (int[] win : winPositions) {
                if (unplayed[win[0]] == unplayed[win[1]]
                        && unplayed[win[1]] == unplayed[win[2]]
                        && unplayed[win[0]] != 2) {
                    gameIsOn = false;
                     winner = "Cross";
                    if (unplayed[win[1]] == 0) {
                        winner = "Zero";
                    }
                    TextView textView = findViewById(R.id.tv);
                    textView.setText(winner + " has won");
                    LinearLayout linearLayout = findViewById(R.id.ll);
                    linearLayout.setVisibility(View.VISIBLE);

                } else {
                    boolean gameOver = true;
                    for (int state : unplayed) {
                        if (state == 2) {
                            gameOver = false;
                        }
                    }
                    if (gameOver && gameIsOn) {
                        TextView textView = findViewById(R.id.tv);
                        textView.setText(" Its a Draw");
                        LinearLayout linearLayout = findViewById(R.id.ll);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }

    public void playAgain(View view) {
        LinearLayout linearLayout = findViewById(R.id.ll);
        linearLayout.setVisibility(View.INVISIBLE);
        gameIsOn = true;
        for (int i = 0; i < unplayed.length; i++) {
            unplayed[i] = 2;
        }
        GridLayout gridLayout = findViewById(R.id.gl);
        for (int j = 0; j < gridLayout.getChildCount(); j++) {
            ((ImageView) gridLayout.getChildAt(j)).setImageResource(0);
        }
    }
}

