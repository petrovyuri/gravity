package com.mrdeveloper.gravity.objects;

import android.graphics.Rect;

import com.mrdeveloper.gravity.clases.GameManager;
import com.mrdeveloper.my_framework.core.AnimationGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.GraphicsGame;
import com.mrdeveloper.my_framework.core.ObjectGame;
import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.my_framework.utilits.UtilTimerDelayGame;

/* Класс главного игрока */
public class MainPlayer extends ObjectGame {

    //region Fields
    private final int GRAVITY_PLAYER = -3;
    private final int MAX_SPEED = 15;
    private final int MIN_SPEED = 1;
    private CoreGame mCoreGame;

    private AnimationGame animMainPlayer;
    private AnimationGame animMainPlayerBoost;
    private AnimationGame animExplosionPlayer;
    private AnimationGame animPlayerShieldsOn;
    private AnimationGame animPlayerShieldsOnBoost;

    private UtilTimerDelayGame timerOnShieldHit;
    private UtilTimerDelayGame timerOnGameOver;
    private UtilTimerDelayGame timerShieldsOn;

    private boolean mBoostingPlayer;
    private int mShieldsPlayer;
    private boolean mHitEnemy;
    private boolean mIsGameOver;
    private static boolean sShieldsOn;
    //endregion

    //region Main methods
    public MainPlayer(CoreGame coreGame, int maxScreenX, int maxScreenY, int minScreenY) {
        init(coreGame, maxScreenX, maxScreenY, minScreenY);
        initAnimation();
    }

    public void update() {
        if (mCoreGame.getTouchListenerFW().getTouchDown(0, pMaxScreenY, pMaxScreenX, pMaxScreenY)) {
            startBoosting();
        }
        if (mCoreGame.getTouchListenerFW().getTouchUp(0, pMaxScreenY, pMaxScreenX, pMaxScreenY)) {
            stopBoosting();
        }
        if (timerShieldsOn.timerDelay(5)) {
            sShieldsOn = false;
        }
        updateBoosting();
        pHitBox = new Rect(pX, pY,
                ResourceGame.sSpritePlayer.get(0).getWidth(),
                ResourceGame.sSpritePlayer.get(0).getHeight());
        if (mIsGameOver) {
            animExplosionPlayer.runAnimation();
        }
    }

    public void drawing(GraphicsGame graphicsGame) {

        if (mIsGameOver) {
            //Проверка, если игра закончилась
            animExplosionPlayer.drawingAnimation(graphicsGame, pX, pY);
            if (timerOnGameOver.timerDelay(0.5)) {
                GameManager.gameOver = true;
            }
        }
        if (mHitEnemy) {
            //Проверка на сталкновение с врагом
            graphicsGame.drawTexture(ResourceGame.sShieldHitEnemy, pX, pY);
            mHitEnemy = !timerOnShieldHit.timerDelay(0.2);
        }

        if (mBoostingPlayer) {
            if (sShieldsOn) {
                animPlayerShieldsOnBoost.drawingAnimation(graphicsGame, pX, pY);
            } else animMainPlayerBoost.drawingAnimation(graphicsGame, pX, pY);
        } else if (sShieldsOn) {
            animPlayerShieldsOn.drawingAnimation(graphicsGame, pX, pY);
        } else animMainPlayer.drawingAnimation(graphicsGame, pX, pY);
    }

    //endregion

    //region Methods
    private void updateBoosting() {
        // Метод проверяет ускоряется ли игрок
        if (mBoostingPlayer) {
            pSpeed += 0.1;
        } else pSpeed -= 3;
        if (pSpeed > MAX_SPEED) {
            pSpeed = MAX_SPEED;
        }
        if (pSpeed < MIN_SPEED) {
            pSpeed = MIN_SPEED;
        }

        pY -= pSpeed + GRAVITY_PLAYER;
        if (pY < pMinScreenY) {
            pY = pMinScreenY;
        }
        if (pY > pMaxScreenY) {
            pY = pMaxScreenY;
        }
        if (mBoostingPlayer) {
            if (sShieldsOn) {
                animPlayerShieldsOnBoost.runAnimation();
            } else animMainPlayerBoost.runAnimation();
        } else if (sShieldsOn) {
            animPlayerShieldsOn.runAnimation();
        } else animMainPlayer.runAnimation();
    }

    private void stopBoosting() {
        mBoostingPlayer = false;
    }

    private void startBoosting() {
        mBoostingPlayer = true;
    }

    public void hitEnemy() {
        //Выполнение когда есть столкновение с врагом
        if (!sShieldsOn) {
            mShieldsPlayer--;
            if (mShieldsPlayer < 0) {
                ResourceGame.sSoundExplode.play(1);
                mIsGameOver = true;
                timerOnGameOver.startTimer();
            }
            mHitEnemy = true;
            timerOnShieldHit.startTimer();
        }
    }

    public void hitProtector() {
        //Выполнение когда есть столкновение с включением защиты
        sShieldsOn = true;
        timerShieldsOn.startTimer();
    }

    private void init(CoreGame coreGame, int maxScreenX, int maxScreenY, int minScreenY) {
        sShieldsOn = false;
        pX = 20;
        pY = 200;
        pSpeed = GameManager.SPEED_ANIMATION;
        mShieldsPlayer = 3000;
        mBoostingPlayer = false;
        mHitEnemy = false;
        mIsGameOver = false;
        pRadius = ResourceGame.sSpritePlayer.get(0).getWidth() / 4;
        timerOnShieldHit = new UtilTimerDelayGame();
        timerOnGameOver = new UtilTimerDelayGame();
        timerShieldsOn = new UtilTimerDelayGame();
        this.mCoreGame = coreGame;
        this.pMaxScreenX = maxScreenX;
        this.pMaxScreenY = maxScreenY - ResourceGame.sSpritePlayer.get(0).getHeight();
        this.pMinScreenY = minScreenY;
    }

    private void initAnimation() {
        animMainPlayer = new AnimationGame(pSpeed,
                ResourceGame.sSpritePlayer.get(0),
                ResourceGame.sSpritePlayer.get(1),
                ResourceGame.sSpritePlayer.get(2),
                ResourceGame.sSpritePlayer.get(3));
        animMainPlayerBoost = new AnimationGame(pSpeed,
                ResourceGame.sSpritePlayerBoost.get(0),
                ResourceGame.sSpritePlayerBoost.get(1),
                ResourceGame.sSpritePlayerBoost.get(2),
                ResourceGame.sSpritePlayerBoost.get(3));
        animExplosionPlayer = new AnimationGame(pSpeed,
                ResourceGame.sSpriteExplosionPlayer.get(0),
                ResourceGame.sSpriteExplosionPlayer.get(1),
                ResourceGame.sSpriteExplosionPlayer.get(2),
                ResourceGame.sSpriteExplosionPlayer.get(3));
        animPlayerShieldsOn = new AnimationGame(pSpeed,
                ResourceGame.sSpritePlayerShieldsOn.get(0),
                ResourceGame.sSpritePlayerShieldsOn.get(1),
                ResourceGame.sSpritePlayerShieldsOn.get(2),
                ResourceGame.sSpritePlayerShieldsOn.get(3));
        animPlayerShieldsOnBoost = new AnimationGame(pSpeed,
                ResourceGame.sSpritePlayerShieldsOnBoost.get(0),
                ResourceGame.sSpritePlayerShieldsOnBoost.get(1),
                ResourceGame.sSpritePlayerShieldsOnBoost.get(2),
                ResourceGame.sSpritePlayerShieldsOnBoost.get(3));
    }
    //endregion

    //region Get&Set
    public double getSpeedPlayer() {
        return pSpeed;
    }

    public int getShieldsPlayer() {
        return mShieldsPlayer;
    }

    public static boolean isShieldsOn() {
        return sShieldsOn;
    }
    //endregion
}
