package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class MainMenuScene extends SceneGame {

    //region Main methods
    public MainMenuScene(CoreGame coreGame) {

        super(coreGame);
    }

    @Override
    public void update() {
        if(pCoreGame.getTouchListenerFW()
                .getTouchUp(20,300,100,50)){
            pCoreGame.setScene(new GameScene(pCoreGame));
            ResourceGame.sSoundTouch.play(1);
        }
        if(pCoreGame.getTouchListenerFW()
                .getTouchUp(20,400,100,50)){
            pCoreGame.setScene(new TopDistanceScene(pCoreGame));
            ResourceGame.sSoundTouch.play(1);
        }

        if(pCoreGame.getTouchListenerFW()
                .getTouchUp(20,350,100,50)){
            pCoreGame.setScene(new SettingsScene(pCoreGame));
            ResourceGame.sSoundTouch.play(1);
        }

        if(pCoreGame.getTouchListenerFW()
                .getTouchUp(20,450,100,50)){
            pCoreGame.setScene(new ExitScene(pCoreGame));
            ResourceGame.sSoundTouch.play(1);
        }
    }

    @Override
    public void drawing() {
        pGraphicsGame.clearScene(Color.BLACK);
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_mainMenu_nameGame)
                , 100, 100,Color.BLUE, 60,ResourceGame.mainMenuFont );
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_mainMenu_newGame), 20, 300,Color.BLUE, 40,ResourceGame.mainMenuFont );
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_mainMenu_settings), 20, 350,Color.BLUE, 40,ResourceGame.mainMenuFont );
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_mainMenu_results), 20, 400,Color.BLUE, 40,ResourceGame.mainMenuFont );
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_mainMenu_exitGame), 20, 450,Color.BLUE, 40,ResourceGame.mainMenuFont );
    }
    //endregion

    //region @Override
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
    //endregion
}
