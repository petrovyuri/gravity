package com.mrdeveloper.gravity.generators;

import android.graphics.Color;

import com.mrdeveloper.gravity.objects.Star;
import com.mrdeveloper.my_framework.core.GraphicsGame;

import java.util.ArrayList;

    /* Класс генерирует задний фон */
public class GeneratorBackground {

    //region Fields
    //Обьявление массива для звезд
    private ArrayList<Star> mStarArrayList = new ArrayList<>();
    //endregion

    //region Main methods
    public GeneratorBackground(int sceneWidth, int sceneHeight, int minScreenY) {
        int starsSpeak = 50;//Максимальное количество звезд
        for (int i = 0; i < starsSpeak; i++) {
            //Цикл создает звезды и помещет их в массив
            Star star = new Star(sceneWidth, sceneHeight, minScreenY);
            mStarArrayList.add(star);
        }
    }

    public void update(double speedPlayer) {
        for (int i = 0; i < mStarArrayList.size(); i++) {
            //Производим обновление каждой звезды в массиве
            mStarArrayList.get(i).update(speedPlayer);
        }
    }

    public void drawing(GraphicsGame graphicsGame) {
        for (int i = 0; i < mStarArrayList.size(); i++) {
            //Прорисовка каждой звезды в массиве
            graphicsGame.drawPixel(mStarArrayList.get(i).getX(),
                    mStarArrayList.get(i).getY(), Color.WHITE);
        }
    }
    //endregion
}
