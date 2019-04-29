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
    AnimationFW animPlayerShieldsOn;
    AnimationFW animPlayerShieldsOnBoost;
    CoreFW coreFW;
    UtilTimerDelay timerOnShieldHit;
    UtilTimerDelay timerOnGameOver;
    UtilTimerDelay timerShieldsOn;

    boolean boosting;
    private int shieldsPlayer;
    boolean hitEnemy;
    boolean isGameOver;
   static boolean shieldsOn;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        shieldsOn = false;
        x = 20;
        y = 200;
        speed = GameManager.SPEED_ANIMATION;
        shieldsPlayer = 3;
        boosting = false;
        hitEnemy = false;
        isGameOver = false;


        radius = UtilResource.spritePlayer.get(0).getWidth() / 4;

        timerOnShieldHit = new UtilTimerDelay();
        timerOnGameOver = new UtilTimerDelay();
        timerShieldsOn = new UtilTimerDelay();

        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.spritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
        initAnimation();
    }

    private void initAnimation() {
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
        animPlayerShieldsOn = new AnimationFW(speed,
                UtilResource.spritePlayerShieldsOn.get(0),
                UtilResource.spritePlayerShieldsOn.get(1),
                UtilResource.spritePlayerShieldsOn.get(2),
                UtilResource.spritePlayerShieldsOn.get(3));
        animPlayerShieldsOnBoost = new AnimationFW(speed,
                UtilResource.spritePlayerShieldsOnBoost.get(0),
                UtilResource.spritePlayerShieldsOnBoost.get(1),
                UtilResource.spritePlayerShieldsOnBoost.get(2),
                UtilResource.spritePlayerShieldsOnBoost.get(3));
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

        if (timerShieldsOn.timerDelay(5)){
            shieldsOn=false;
        }

        if (boosting) {
            if (shieldsOn) {
                animPlayerShieldsOnBoost.runAnimation();
            } else animMainPlayerBoost.runAnimation();
        } else if (shieldsOn) {
            animPlayerShieldsOn.runAnimation();
        } else animMainPlayer.runAnimation();

        hitBox = new Rect(x, y,
                UtilResource.spritePlayer.get(0).getWidth(),
                UtilResource.spritePlayer.get(0).getHeight());
        if (isGameOver) {
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

        if (!isGameOver) {
            if (!hitEnemy) {
                if (boosting) {
                    if (shieldsOn) {
                        animPlayerShieldsOnBoost.drawingAnimation(graphicsFW, x, y);
                    } else animMainPlayerBoost.drawingAnimation(graphicsFW, x, y);
                } else if (shieldsOn) {
                    animPlayerShieldsOn.drawingAnimation(graphicsFW, x, y);
                } else animMainPlayer.drawingAnimation(graphicsFW, x, y);
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy, x, y);
                if (timerOnShieldHit.timerDelay(0.2)) {
                    hitEnemy = false;
                } else hitEnemy = true;
            }
        } else {
            animExplosionPlayer.drawingAnimation(graphicsFW, x, y);
            if (timerOnGameOver.timerDelay(0.5)) {
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
        if (!shieldsOn){
            shieldsPlayer--;
            if (shieldsPlayer < 0) {
                UtilResource.explode.play(1);
                isGameOver = true;
                timerOnGameOver.startTimer();
            }
            hitEnemy = true;
            timerOnShieldHit.startTimer();
        }
    }

    public static boolean isShieldsOn() {
        return shieldsOn;
    }

    public void hitProtector(){
        shieldsOn=true;
        timerShieldsOn.startTimer();
    }
}
