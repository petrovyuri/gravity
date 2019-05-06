package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.gravity.clases.GameManager;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class GameScene extends SceneGame {

    //region Fields
    private GameState mGameState;
    private GameManager mGameManager;

    //Состояния игры
    enum GameState {
        READY, RUNNING, PAUSE, GAME_OVER
    }
    //endregion

    //region Main methods
    public GameScene(CoreGame coreGame) {
        super(coreGame);
        init(coreGame);
        ResourceGame.sMainMusicGame.play(true, 0.5f);
    }

    private void init(CoreGame coreGame) {
        mGameState = GameState.READY;
        mGameManager = new GameManager(coreGame, sceneWidth, sceneHeight);
    }

    @Override
    public void update() {
        //Обновление игры
        if (mGameState == GameState.READY) {
            updateStateReady();
        }
        if (mGameState == GameState.RUNNING) {
            updateStateRunning();
        }
        if (mGameState == GameState.PAUSE) {
            updateStatePause();
        }
        if (mGameState == GameState.GAME_OVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        //Перерисовка игры
        graphicsGame.clearScene(Color.BLACK);

        if (mGameState == GameState.READY) {
            drawingStateReady();
        }
        if (mGameState == GameState.RUNNING) {
            drawingStateRunning();
        }
        if (mGameState == GameState.PAUSE) {
            drawingStatePause();
        }
        if (mGameState == GameState.GAME_OVER) {
            drawingStateGameOver();
        }

    }
    //endregion

    //region Update state
    private void updateStateGameOver() {
        SettingsGame.addDistance(mGameManager.getPassedDistance());
        if (coreGame.getTouchListenerFW().getTouchUp(250, 360, 100, 35)) {
            coreGame.setScene(new GameScene(coreGame));
        }
        if (coreGame.getTouchListenerFW().getTouchUp(250, 420, 100, 35)) {
            coreGame.setScene(new MainMenuScene(coreGame));
        }

    }

    private void updateStatePause() {

    }

    private void updateStateRunning() {
        mGameManager.update();
        if (GameManager.gameOver) {
            mGameState = GameState.GAME_OVER;
        }
    }

    private void updateStateReady() {
        if (coreGame.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            mGameState = GameState.RUNNING;
        }
    }
    //endregion

    //region Drawing state
    private void drawingStateGameOver() {
        graphicsGame.clearScene(Color.BLACK);
        graphicsGame.drawText(coreGame.getString(R.string.txt_gameScene_stateGameOver_gameOver),
                250, 300, Color.WHITE, 60, null);
        graphicsGame.drawText(coreGame.getString(R.string.txt_gameScene_stateGameOver_restart),
                250, 360, Color.WHITE, 30, null);
        graphicsGame.drawText(coreGame.getString(R.string.txt_gameScene_stateGameOver_exit),
                250, 420, Color.WHITE, 30, null);
        graphicsGame.drawText(coreGame.getString(R.string.txt_gameScene_stateGameOver_distance) + ":" + mGameManager.getPassedDistance(),
                250, 200, Color.WHITE, 30, null);
    }

    private void drawingStatePause() {

    }

    private void drawingStateRunning() {
        graphicsGame.clearScene(Color.BLACK);
        mGameManager.drawing(graphicsGame);
    }

    private void drawingStateReady() {
        graphicsGame.clearScene(Color.BLACK);
        graphicsGame.drawText(coreGame.getString(R.string.txt_gameScene_stateReady_ready),
                250, 300, Color.WHITE, 60, null);
    }
    //endregion

    //region @Override
    @Override
    public void pause() {
        ResourceGame.sMainMusicGame.stop();
    }

    @Override
    public void resume() {
        ResourceGame.sMainMusicGame.play(true, 1f);
    }

    @Override
    public void dispose() {
        ResourceGame.sSoundExplode.dispose();
        ResourceGame.sSoundHit.dispose();
        ResourceGame.sSoundTouch.dispose();
        ResourceGame.sMainMusicGame.dispose();
    }
    //endregion
}
