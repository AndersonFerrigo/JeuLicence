package com.pt.jeulicense;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pt.jeulicense.Entities.Game;
import com.pt.jeulicense.widgets.DrawingPanel;

public class GameActivity extends AppCompatActivity {
    private DrawingPanel drawingPanel;
    private TextView timerText;
    private TextView score;
    private Game game;
    private ProgressBar circularBar;
    String receivedScore;
    String finalScoreText;
    String currentOrientation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        try{
            savedInstanceState = getIntent().getExtras();
            currentOrientation= savedInstanceState.getString("orientation");
            receivedScore = savedInstanceState.getString("score");
        }
        catch (Exception e) {
            e.getMessage();
        }

        drawingPanel = findViewById(R.id.drawingPanel);
        circularBar = findViewById(R.id.progress_circular);
        score = findViewById(R.id.game_score);
        score.setText(receivedScore);
        drawingPanel.post(new Runnable() {
            @Override
            public void run() {
                createGame(drawingPanel.getMeasuredWidth(), drawingPanel.getMeasuredHeight(),
                                                                currentOrientation, receivedScore);
                game.startGame();
            }
        });
    }

    private void createGame(int x, int y, String orientation, String score) {
        game = new Game(x, y, this, orientation, score);
    }

    public void updateTimerText(final int time) {
        final int seconds = time % 60;
        int minutes = time / 60;
        final String maxTime = String.format("%02d", 01) + ":" + String.format("%02d", 00);
        final String timeString = String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                timerText.setText(timeString);
                if (timeString.equals(maxTime)) {
                    Intent retornarPrincipal = new Intent(getApplicationContext(), MainActivity.class);
                    finalScoreText = score.getText().toString();
                    retornarPrincipal.putExtra("flag", currentOrientation);
                    retornarPrincipal.putExtra("score", finalScoreText);
                    startActivity(retornarPrincipal);
                    try {
                        this.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        });
    }

    public void updateDrawingPanel(final Game game) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                drawingPanel.update(game);
            }
        });
    }

    public void showScoreMessage(final int currentScore) {
        this.runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
            GameActivity.this.score.setText(String.valueOf(currentScore));
              }
                           }
        );
    }

    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }
}

