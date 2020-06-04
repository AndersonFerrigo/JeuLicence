package com.pt.jeulicense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button normalGame;
    Button reverseGame;
    Button exitGame;
    Bundle receiveScore = null;
    TextView scoreN;
    TextView scoreR;

    String receiveFlag;
    String scoreGame;
    String receiveScoreShared;

    String retrieveSN;
    String retrieveSR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normalGame = findViewById(R.id.btnNormalGame);
        reverseGame = findViewById(R.id.btnReverseGame);
        exitGame = findViewById(R.id.btnExitGame);

        scoreN = findViewById(R.id.valorScoreNormale);
        scoreR = findViewById(R.id.valorScoreReversee);

        try {

            // Retrieve the instances of preferences
            SharedPreferences preferences = getSharedPreferences("my_prefs_data", MODE_PRIVATE);

            // Retrieve the score
            // The second parameter is a default valor if that this parameter doesn't exist.
            retrieveSN = preferences.getString("orientationNormale", "");
            retrieveSR = preferences.getString("orientationReversee", "");

            scoreN.setText(retrieveSN);
            scoreR.setText(retrieveSR);

            } catch (Exception e) {
                e.getMessage();
                }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            receiveScore = getIntent().getExtras();
            receiveFlag = receiveScore.getString("flag");
            scoreGame = receiveScore.getString("score");

            if (receiveFlag.equals("orientationNormale")) {
                scoreN.setText(scoreGame);
                } else if (receiveFlag.equals("orientationReversee")) {
                    scoreR.setText(scoreGame);
                    }
            }catch (Exception e){
                e.getMessage();
                }

        normalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String informationNormal = "orientationNormale";
             Intent intentNormal = new Intent(
             getApplicationContext(), GameActivity.class);
             intentNormal.putExtra("orientation", informationNormal);
             intentNormal.putExtra("score", scoreN.getText().toString());
             startActivity(intentNormal);
            }
        });

        reverseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReversee = new Intent(
                        getApplicationContext(), GameActivity.class);
                String informationRev = "orientationReversee";
                intentReversee.putExtra("orientation", informationRev);
                intentReversee.putExtra("score", scoreR.getText().toString());
                startActivity(intentReversee);
            }
        });

        exitGame.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveScore();
        }
    });
    }

    public void saveScore(){
        // Retrieve the instance of preferences
        SharedPreferences preferences = getSharedPreferences("my_prefs_data", MODE_PRIVATE);

        // To record yhe data, this get the editor instance
        SharedPreferences.Editor editor = preferences.edit();

        // Set que map (key, value)
        editor.putString("orientationNormale", scoreN.getText().toString());
        editor.putString("orientationReversee", scoreR.getText().toString());

        // Persist the datas
        // If you forgot call commit statement, the data won´t be record
        editor.commit();

        // Call the method to clase the app
        exit();
    }

    public void exit(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
            }
    }

    @Override
    public void onBackPressed() {
    // super.onBackPressed();
    // Not calling **super**, disables back button in current screen.
    }
}
