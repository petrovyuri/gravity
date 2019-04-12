package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.gravity.clases.GameManager;
import com.mrdeveloper.gravity.generators.GeneratorBackground;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.my_framework.CoreFW;
import com.mrdeveloper.my_framework.SceneFW;

public class GameScene extends SceneFW {

    enum GameState {
        READY, RUNNING, PAUSE, GAMEOVER
    }

    GameState gameState;

    GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;

        gameManager=new GameManager(coreFW, sceneWidth,sceneHeight);
    }

    @Override
    public void update() {
        if (gameState == GameState.READY) {
            updateStateReady();
        }
        if (gameState == GameState.RUNNING) {
            updateStateRunning();
        }
        if (gameState == GameState.PAUSE) {
            updateStatePause();
        }
        if (gameState == GameState.GAMEOVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);

        if (gameState == GameState.READY) {
            drawingStateReady();
        }
        if (gameState == GameState.RUNNING) {
            drawingStateRunning();
        }
        if (gameState == GameState.PAUSE) {
            drawingStatePause();
        }
        if (gameState == GameState.GAMEOVER) {
            drawingStateGameOver();
        }


    }


    private void drawingStateGameOver() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_gameOver),
                250,300,Color.WHITE, 60,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_restart),
                250,360,Color.WHITE, 30,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_exit),
                250,420,Color.WHITE, 30,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_distance)+":"+gameManager.getPassedDistance(),
                250,200,Color.WHITE, 30,null);
    }

    private void updateStateGameOver() {
        SettingsGame.addDistance(gameManager.getPassedDistance());
        if( coreFW.getTouchListenerFW().getTouchUp(250,360,100,35)){
            coreFW.setScene(new GameScene(coreFW));
        }
        if( coreFW.getTouchListenerFW().getTouchUp(250,420,100,35)){
            coreFW.setScene(new MainMenuScene(coreFW));
        }

    }

    private void drawingStatePause() {

    }

    private void updateStatePause() {

    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
        gameManager.drawing(coreFW,graphicsFW);
    }

    private void updateStateRunning() {
        gameManager.update();
        if (GameManager.gameOver){
            gameState = GameState.GAMEOVER;
        }
    }

    private void drawingStateReady() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready),
                250, 300, Color.WHITE, 60, null);
    }

    private void updateStateReady() {
       if( coreFW.getTouchListenerFW().getTouchUp(0,sceneHeight,sceneWidth,sceneHeight)){
           gameState=GameState.RUNNING;
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
