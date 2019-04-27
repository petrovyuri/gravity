package com.mrdeveloper.gravity.objects;

import android.graphics.Rect;

import com.mrdeveloper.gravity.clases.GameManager;
import com.mrdeveloper.my_framework.AnimationFW;
import com.mrdeveloper.my_framework.CoreFW;
import com.mrdeveloper.my_framework.GraphicsFW;
import com.mrdeveloper.my_framework.ObjectFW;
import com.mrdeveloper.gravity.utilits.UtilResource;
import com.mrdeveloper.my_framework.utilits.UtilTimerDelay;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;
    AnimationFW animMainPlayer;
    AnimationFW animMainPlayerBoost;
    AnimationFW animExplosionPlayer;
    CoreFW coreFW;
    UtilTimerDelay timerOnShieldHit;
    UtilTimerDelay timerOnGameOver;

    boolean boosting;
    private int shieldsPlayer;
    boolean hitEnemy;
    boolean isGameOver;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 3;
        shieldsPlayer = 3;
        boosting = false;
        hitEnemy = false;
        isGameOver = false;

        radius = UtilResource.spritePlayer.get(0).getWidth()/4;

        timerOnShieldHit = new UtilTimerDelay();
        timerOnGameOver = new UtilTimerDelay();

        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.spritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
        animMainPlayer = new AnimationFW(speed, UtilResource.spritePlayer.get(0),
                UtilResource.spritePlayer.get(1),
                UtilResource.spritePlayer.get(2),
                UtilResource.spritePlayer.get(3));
        animMainPlayerBoost = new AnimationFW(speed, UtilResource.spritePlayerBoost.get(0),
                UtilResource.spritePlayerBoost.get(1),
                UtilResource.spritePlayerBoost.get(2),
                UtilResource.spritePlayerBoost.get(3));
        animExplosionPlayer = new AnimationFW(speed,
                UtilResource.spriteExplosinPlayer.get(0),
                UtilResource.spriteExplosinPlayer.get(1),
                UtilResource.spriteExplosinPlayer.get(2),
                UtilResource.spriteExplosinPlayer.get(3));
    }

    public void update() {
        if (coreFW.getTouchListenerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }
        if (coreFW.getTouchListenerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }

        if (boosting) {
            speed += 0.1;
        } else speed -= 3;
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        y -= speed + GRAVITY;
        if (y < minScreenY) {
            y = minScreenY;
        }
        if (y > maxScreenY) {
            y = maxScreenY;
        }

        if (boosting) {
            animMainPlayerBoost.runAnimation();
        } else animMainPlayer.runAnimation();

        hitBox = new Rect(x,y,
                UtilResource.spritePlayer.get(0).getWidth(),
                UtilResource.spritePlayer.get(0).getHeight());
        if (isGameOver){
            animExplosionPlayer.runAnimation();
        }
    }

    private void stopBoosting() {
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {

        if (!isGameOver){
            if(!hitEnemy){
                if (boosting) {
                    animMainPlayerBoost.drawingAnimation(graphicsFW, x, y);
                } else animMainPlayer.drawingAnimation(graphicsFW, x, y);
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy,x,y);
                if (timerOnShieldHit.timerDelay(0.2)){
                    hitEnemy =false;
                } else hitEnemy = true;
            }
        } else {
            animExplosionPlayer.drawingAnimation(graphicsFW,x,y);
            if (timerOnGameOver.timerDelay(0.5)){
                GameManager.gameOver = true;
            }
        }



    }

    public double getSpeedPlayer() {
        return speed;
    }

    public int getShieldsPlayer() {
        return shieldsPlayer;
    }

    public void hitEnemy() {
        shieldsPlayer--;
        if (shieldsPlayer<0){
            UtilResource.explode.play(1);
            isGameOver=true;
            timerOnGameOver.startTimer();
        }
        hitEnemy = true;
        timerOnShieldHit.startTimer();
    }
}
