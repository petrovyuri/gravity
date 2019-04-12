package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.my_framework.CoreFW;
import com.mrdeveloper.my_framework.SceneFW;

public class TopDistance extends SceneFW {

    String[] numbers = new String[5];

    public TopDistance(CoreFW coreFW) {
        super(coreFW);
        for (int i = 0; i < 5; i++) {
            this.numbers[i] = " " + (i + 1) + "." + SettingsGame.distance[i];
        }
    }

    @Override
    public void update() {
        if (coreFW.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }
    }

    @Override
    public void drawing() {
        graphicsFW.drawText(coreFW.getString(R.string.txt_top_distance), 120, 200, Color.GREEN, 40, null);
        graphicsFW.drawText(String.valueOf(numbers[0]), 120, 250, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(numbers[1]), 120, 300, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(numbers[2]), 120, 350, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(numbers[3]), 120, 400, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(numbers[4]), 120, 450, Color.GREEN, 35, null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        graphicsFW.clearScene(Color.BLACK);
    }

    @Override
    public void dispose() {

    }
}
