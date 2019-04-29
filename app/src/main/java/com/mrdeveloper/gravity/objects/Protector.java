package com.mrdeveloper.gravity.objects;

import android.graphics.Rect;

import com.mrdeveloper.gravity.clases.GameManager;
import com.mrdeveloper.gravity.utilits.UtilResource;
import com.mrdeveloper.my_framework.AnimationFW;
import com.mrdeveloper.my_framework.GraphicsFW;
import com.mrdeveloper.my_framework.ObjectFW;
import com.mrdeveloper.my_framework.utilits.UtilRandomFW;

public class Protector extends ObjectFW {

    AnimationFW animProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.spriteProtector.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = UtilResource.spriteProtector.get(0).getWidth() / 2;
        hitBox = new Rect(x, y,
                UtilResource.spriteProtector.get(0).getWidth(),
                UtilResource.spriteProtector.get(0).getHeight());
        animProtector = new AnimationFW(GameManager.SPEED_ANIMATION,
                UtilResource.spriteProtector.get(0),
                UtilResource.spriteProtector.get(1),
                UtilResource.spriteProtector.get(2),
                UtilResource.spriteProtector.get(3));
    }

    public void update(double speedPlayer) {
        x -= speed;
        x -= speedPlayer;
        if (x < minScreenX) {
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        animProtector.runAnimation();

        hitBox = new Rect(x, y,
                UtilResource.spriteEnemy.get(0).getWidth(),
                UtilResource.spriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        animProtector.drawingAnimation(graphicsFW, x, y);
    }
}
