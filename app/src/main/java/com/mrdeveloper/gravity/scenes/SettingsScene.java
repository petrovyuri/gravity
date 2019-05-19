package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;

import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class SettingsScene extends SceneGame {

    SettingsScene(CoreGame coreGame) {
        super(coreGame);
    }

    @Override
    protected void update() {
        if (pCoreGame.isPressedKeyBack()){
            pCoreGame.setPressedKeyBack(false);
            pCoreGame.setScene(new MainMenuScene(pCoreGame));
        }
        if (pCoreGame.getTouchListenerFW().getTouchUp(400,350,100,30)){
            SettingsGame.sSoundOn =!SettingsGame.sSoundOn;
            SettingsGame.saveSettings(pCoreGame);
        }

        if (pCoreGame.getTouchListenerFW().getTouchUp(400,300,100,30)){
            SettingsGame.sMusicOn =!SettingsGame.sMusicOn;
            SettingsGame.saveSettings(pCoreGame);
        }
    }

    @Override
    protected void drawing() {
        pCoreGame.getGraphicsFW().clearScene(Color.BLACK);
        pCoreGame.getGraphicsFW().drawText("Настройки",
                250, 200, Color.GREEN, 40, null);

        pCoreGame.getGraphicsFW().drawText("Музыка ",
                250, 300, Color.GREEN, 30, null);
        pCoreGame.getGraphicsFW().drawText("Звук ",
                250, 350, Color.GREEN, 30, null);

        if (SettingsGame.sMusicOn){
            pCoreGame.getGraphicsFW().drawText("Включено ",
                    400, 300, Color.GREEN, 30, null);
        } else pCoreGame.getGraphicsFW().drawText("Выключено ",
                400, 300, Color.RED, 30, null);

        if (SettingsGame.sSoundOn){
            pCoreGame.getGraphicsFW().drawText("Включено ",
                    400, 350, Color.GREEN, 30, null);
        } else pCoreGame.getGraphicsFW().drawText("Выключено ",
                400, 350, Color.RED, 30, null);


    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void dispose() {

    }
}
