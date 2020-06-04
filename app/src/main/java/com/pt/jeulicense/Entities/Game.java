package com.pt.jeulicense.Entities;

import android.widget.TextView;
import com.pt.jeulicense.GameActivity;
import com.pt.jeulicense.handlers.AccelerometerHandler;
import com.pt.jeulicense.handlers.ReverseAccelerometerHandler;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Runnable{

    public static int UPDATES_PER_SECOND = 60;
    public int scoreGame;
    public TextView score;
    private Field field;
    private Chrono chrono;
    private GameActivity activity;
    private Timer updateTimer;
    private AccelerometerHandler sensor;
    private ReverseAccelerometerHandler reverseSensor;
    private String orientationCurrent;

    public Game(int fieldHeight, int fieldWidth, GameActivity activity, String orientaion, String score){
        field = new Field(fieldHeight, fieldWidth);
        sensor = new AccelerometerHandler(activity);
        reverseSensor = new ReverseAccelerometerHandler(activity);
        chrono = new Chrono();
        chrono.addListener(this);
        this.activity = activity;
        updateTimer = new Timer();
        activity.updateDrawingPanel(this);
        orientationCurrent = orientaion;
        if(score.equals("")){
            scoreGame = 0;
        }else {
            scoreGame = Integer.parseInt(score);
        }
    }

    public Field getField() {
        return field;
    }

    public void startGame() {
        chrono.start();
        newTimer();
        long delay = 1000 / Game.UPDATES_PER_SECOND;
        updateTimer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    update();
                }

            }, delay, delay);
    }

    public void stopGame(){
        field = new Field(field.getHeight(), field.getWidth());
        scoreGame += 5;
        activity.showScoreMessage(scoreGame);
        activity.updateDrawingPanel(this);
    }

    public void winGame(){
        stopGame();
    }

    public void update(){
        if(orientationCurrent.equals("orientationNormale")) {
            field.update(-sensor.getAxisX(), sensor.getAxisY());
        }else{
            field.update(- reverseSensor.getReverseAxisX(),reverseSensor.getReverseAxisY());
        }

        for(Object o : field.getHoles().toArray()){
            Hole h = (Hole) o;
           if(field.distanceToBall(h) < 5){
               if(field.getHoles().removeFirst().equals(h)){
                    if(field.getHoles().isEmpty())
                        winGame();
                }
            }
        }
        activity.updateDrawingPanel(this);
    }

    @Override
    public void run() {
        activity.updateTimerText(chrono.getTimeInSeconds());
    }

    private void newTimer(){
        try{
            updateTimer.cancel();
            updateTimer = new Timer();
        }catch(IllegalStateException e){}
    }
}
