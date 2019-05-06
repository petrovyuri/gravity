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
    static boolean sShieldsOn;
    //endregion

    //region Main methods
    public MainPlayer(CoreGame coreGame, int maxScreenX, int maxScreenY, int minScreenY) {
        init(coreGame, maxScreenX, maxScreenY, minScreenY);
        initAnimation();
    }

    public void update() {
        if (mCoreGame.getTouchListenerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }
        if (mCoreGame.getTouchListenerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }
        if (timerShieldsOn.timerDelay(5)) {
            sShieldsOn = false;
        }
        updateBoosting();
        hitBox = new Rect(x, y,
                ResourceGame.sSpritePlayer.get(0).getWidth(),
                ResourceGame.sSpritePlayer.get(0).getHeight());
        if (mIsGameOver) {
            animExplosionPlayer.runAnimation();
        }
    }

    public void drawing(GraphicsGame graphicsGame) {
        if (!mIsGameOver) {
            if (!mHitEnemy) {
                if (mBoostingPlayer) {
                    if (sShieldsOn) {
                        animPlayerShieldsOnBoost.drawingAnimation(graphicsGame, x, y);
                    } else animMainPlayerBoost.drawingAnimation(graphicsGame, x, y);
                } else if (sShieldsOn) {
                    animPlayerShieldsOn.drawingAnimation(graphicsGame, x, y);
                } else animMainPlayer.drawingAnimation(graphicsGame, x, y);
            } else {
                graphicsGame.drawTexture(ResourceGame.sShieldHitEnemy, x, y);
                mHitEnemy = !timerOnShieldHit.timerDelay(0.2);
            }
        } else {
            animExplosionPlayer.drawingAnimation(graphicsGame, x, y);
            if (timerOnGameOver.timerDelay(0.5)) {
                GameManager.gameOver = true;
            }
        }


    }
    //endregion

    //region Methods
    private void updateBoosting() {
        // Метод проверяет ускоряется ли игрок
        if (mBoostingPlayer) {
            speed += 0.1;
        } else speed -= 3;
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        y -= speed + GRAVITY_PLAYER;
        if (y < minScreenY) {
            y = minScreenY;
        }
        if (y > maxScreenY) {
            y = maxScreenY;
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
        x = 20;
        y = 200;
        speed = GameManager.SPEED_ANIMATION;
        mShieldsPlayer = 3;
        mBoostingPlayer = false;
        mHitEnemy = false;
        mIsGameOver = false;
        radius = ResourceGame.sSpritePlayer.get(0).getWidth() / 4;
        timerOnShieldHit = new UtilTimerDelayGame();
        timerOnGameOver = new UtilTimerDelayGame();
        timerShieldsOn = new UtilTimerDelayGame();
        this.mCoreGame = coreGame;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - ResourceGame.sSpritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
    }

    private void initAnimation() {
        animMainPlayer = new AnimationGame(speed,
                ResourceGame.sSpritePlayer.get(0),
                ResourceGame.sSpritePlayer.get(1),
                ResourceGame.sSpritePlayer.get(2),
                ResourceGame.sSpritePlayer.get(3));
        animMainPlayerBoost = new AnimationGame(speed,
                ResourceGame.sSpritePlayerBoost.get(0),
                ResourceGame.sSpritePlayerBoost.get(1),
                ResourceGame.sSpritePlayerBoost.get(2),
                ResourceGame.sSpritePlayerBoost.get(3));
        animExplosionPlayer = new AnimationGame(speed,
                ResourceGame.sSpriteExplosionPlayer.get(0),
                ResourceGame.sSpriteExplosionPlayer.get(1),
                ResourceGame.sSpriteExplosionPlayer.get(2),
                ResourceGame.sSpriteExplosionPlayer.get(3));
        animPlayerShieldsOn = new AnimationGame(speed,
                ResourceGame.sSpritePlayerShieldsOn.get(0),
                ResourceGame.sSpritePlayerShieldsOn.get(1),
                ResourceGame.sSpritePlayerShieldsOn.get(2),
                ResourceGame.sSpritePlayerShieldsOn.get(3));
        animPlayerShieldsOnBoost = new AnimationGame(speed,
                ResourceGame.sSpritePlayerShieldsOnBoost.get(0),
                ResourceGame.sSpritePlayerShieldsOnBoost.get(1),
                ResourceGame.sSpritePlayerShieldsOnBoost.get(2),
                ResourceGame.sSpritePlayerShieldsOnBoost.get(3));
    }
    //endregion

    //region Get&Set
    public double getSpeedPlayer() {
        return speed;
    }

    public int getShieldsPlayer() {
        return mShieldsPlayer;
    }

    public static boolean isShieldsOn() {
        return sShieldsOn;
    }
    //endregion
}
