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
        this.pMaxScreenX = maxScreenX;
        this.pMaxScreenY = maxScreenY - ResourceGame.sSpriteEnemy.get(0).getHeight();
        this.pMinScreenY = minScreenY;
        this.pMinScreenX = 0;
        pX = maxScreenX;
        pY = UtilRandomGame.getGap(minScreenY, maxScreenY);
        pRadius = ResourceGame.sSpriteEnemy.get(0).getWidth() / 4;
    }

    public void update(double speedPlayer) {
        pX -= pSpeed;
        pX -= speedPlayer;
        if (pX < pMinScreenX) {
            pX = pMaxScreenX;
            pY = UtilRandomGame.getGap(pMinScreenY, pMaxScreenY);
        }
        mAnimEnemy.runAnimation();
        pHitBox = new Rect(pX, pY,
                ResourceGame.sSpriteEnemy.get(0).getWidth(),
                ResourceGame.sSpriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsGame graphicsGame) {
        mAnimEnemy.drawingAnimation(graphicsGame, pX, pY);
    }
    //endregion

    //region Methods
    private void initTypeEnemy(int enemyType) {
        switch (enemyType) {
            case 1:
                pSpeed = UtilRandomGame.getGap(1, 6);
                mAnimEnemy = new AnimationGame(3,
                        ResourceGame.sSpriteEnemy.get(0),
                        ResourceGame.sSpriteEnemy.get(1),
                        ResourceGame.sSpriteEnemy.get(2),
                        ResourceGame.sSpriteEnemy.get(3));
                break;
            case 2:
                pSpeed = UtilRandomGame.getGap(4, 9);
                break;
        }
    }
    //endregion
}
