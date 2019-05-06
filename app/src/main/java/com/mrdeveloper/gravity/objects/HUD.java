package com.mrdeveloper.gravity.objects;

import android.graphics.Color;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.GraphicsGame;


    /* Класс генерирует и обновляет HUD */
public class HUD {

    private int mPassedDistance;
    private int mCurrentSpeedPlayer;
    private int mCurrentShieldsPlayer;

    private final CoreGame CORE_GAME;
    private final int HEIGHT_HUD = 50;

    public HUD(CoreGame coreGame) {
        this.CORE_GAME = coreGame;
    }

    public void update(int passedDistance, int currentSpeedPlayer, int currentShieldsPlayer) {
        this.mPassedDistance = passedDistance;
        this.mCurrentSpeedPlayer = currentSpeedPlayer;
        this.mCurrentShieldsPlayer = currentShieldsPlayer;
    }

    public void drawing(GraphicsGame graphicsGame){
        graphicsGame.drawLine(0,HEIGHT_HUD, graphicsGame.getWidthFrameBuffer(),HEIGHT_HUD, Color.WHITE);
        graphicsGame.drawText(CORE_GAME.getString(R.string.txt_hud_passedDistance)+":"+ mPassedDistance,
                10,30,Color.GREEN,25,null);
        graphicsGame.drawText(CORE_GAME.getString(R.string.txt_hud_currentSpeedPlayer)+":"+ mCurrentSpeedPlayer,
                350,30,Color.GREEN,25,null);
        graphicsGame.drawText(CORE_GAME.getString(R.string.txt_hud_currentShieldsPlayer)+":"+ mCurrentShieldsPlayer,
                650,30,Color.GREEN,25,null);
    }

    public int getHEIGHT_HUD() {
        return HEIGHT_HUD;
    }
}
