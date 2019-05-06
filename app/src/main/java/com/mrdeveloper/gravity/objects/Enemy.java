package com.mrdeveloper.gravity.objects;

import android.graphics.Rect;

import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.my_framework.core.AnimationGame;
import com.mrdeveloper.my_framework.core.GraphicsGame;
import com.mrdeveloper.my_framework.core.ObjectGame;
import com.mrdeveloper.my_framework.utilits.UtilRandomGame;

    /* Класс обьектов - врагов, генерирует врагов разных типов и свойствами,
     * в зависимости от входных аргументов */
public class Enemy extends ObjectGame {

    //region Fields
    private AnimationGame mAnimEnemy;
    //endregion

    //region Main methods
    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        init(maxScreenX, maxScreenY, minScreenY);
        initTypeEnemy(enemyType);
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - ResourceGame.sSpriteEnemy.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX;
        y = UtilRandomGame.getGap(minScreenY, maxScreenY);
        radius = ResourceGame.sSpriteEnemy.get(0).getWidth() / 4;
    }

    public void update(double speedPlayer) {
        x -= speed;
        x -= speedPlayer;
        if (x < minScreenX) {
            x = maxScreenX;
            y = UtilRandomGame.getGap(minScreenY, maxScreenY);
        }
        mAnimEnemy.runAnimation();
        hitBox = new Rect(x, y,
                ResourceGame.sSpriteEnemy.get(0).getWidth(),
                ResourceGame.sSpriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsGame graphicsGame) {
        mAnimEnemy.drawingAnimation(graphicsGame, x, y);
    }
    //endregion

    //region Methods
    private void initTypeEnemy(int enemyType) {
        switch (enemyType) {
            case 1:
                speed = UtilRandomGame.getGap(1, 6);
                mAnimEnemy = new AnimationGame(3,
                        ResourceGame.sSpriteEnemy.get(0),
                        ResourceGame.sSpriteEnemy.get(1),
                        ResourceGame.sSpriteEnemy.get(2),
                        ResourceGame.sSpriteEnemy.get(3));
                break;
            case 2:
                speed = UtilRandomGame.getGap(4, 9);
                break;
        }
    }
    //endregion
}
