package com.mrdeveloper.gravity.objects;

import android.graphics.Rect;

import com.mrdeveloper.gravity.clases.GameManager;
import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.my_framework.core.AnimationGame;
import com.mrdeveloper.my_framework.core.GraphicsGame;
import com.mrdeveloper.my_framework.core.ObjectGame;
import com.mrdeveloper.my_framework.utilits.UtilRandomGame;

public class Protector extends ObjectGame {

    //region Fields
    private AnimationGame mAnimProtector;
    //endregion

    //region Main methods
    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {
        init(maxScreenX, maxScreenY, minScreenY);
    }

    public void update(double speedPlayer) {
        x -= speed;
        x -= speedPlayer;
        if (x < minScreenX) {
            y = UtilRandomGame.getGap(minScreenY, maxScreenY);
        }
        mAnimProtector.runAnimation();

        hitBox = new Rect(x, y,
                ResourceGame.sSpriteEnemy.get(0).getWidth(),
                ResourceGame.sSpriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsGame graphicsGame) {
        mAnimProtector.drawingAnimation(graphicsGame, x, y);
    }
    //endregion

    //region Methods
    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - ResourceGame.sSpriteProtector.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX;
        y = UtilRandomGame.getGap(minScreenY, maxScreenY);
        radius = ResourceGame.sSpriteProtector.get(0).getWidth() / 2;
        hitBox = new Rect(x, y,
                ResourceGame.sSpriteProtector.get(0).getWidth(),
                ResourceGame.sSpriteProtector.get(0).getHeight());
        mAnimProtector = new AnimationGame(GameManager.SPEED_ANIMATION,
                ResourceGame.sSpriteProtector.get(0),
                ResourceGame.sSpriteProtector.get(1),
                ResourceGame.sSpriteProtector.get(2),
                ResourceGame.sSpriteProtector.get(3));
    }
    //endregion
}
