package com.mrdeveloper.my_framework.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Date;

public class LoopGame extends SurfaceView implements Runnable {

    //region Fields
    private final float FPS = 60;
    private final float SECOND = 1000000000;
    private final float UPDATE_TIME = SECOND / FPS;

    private boolean mRunning = false;

    private Thread mGameThread = null;
    private CoreGame mCoreGame;
    private Bitmap mFrameBuffer;
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private Rect mRect;
    //endregion

    public LoopGame(CoreGame coreGame, Bitmap frameBuffer) {
        super(coreGame);
        mFrameBuffer = frameBuffer;
        mCoreGame = coreGame;
        mSurfaceHolder = getHolder();
        mRect = new Rect();
        mCanvas = new Canvas();
    }

    //TEMP
    private float mUpdates = 0;
    private float mDrawing = 0;
    private long mTimer = 0;
    //TEMP

    @Override
    public void run() {

        float lastTime = System.nanoTime();
        float delta = 0;
        mTimer = System.currentTimeMillis();

        while (mRunning) {
            float nowTime = System.nanoTime();
            float elapsedTime = nowTime - lastTime;
            lastTime = nowTime;
            delta += elapsedTime / UPDATE_TIME;
            if (delta > 1) {
                updateGame();
                drawingGame();
                delta--;
            }
            if (System.currentTimeMillis() - mTimer > 1000) {
                Date date = new Date();
                System.out.println("UPDATES = " + mUpdates + " DRAWING " + mDrawing + " " + date.toString());
                mUpdates = 0;
                mDrawing = 0;
                mTimer += 1000;
            }
        }
    }

    private void updateGame() {
        mUpdates++;
        mCoreGame.getCurrentScene().update();
    }

    private void drawingGame() {
        mDrawing++;
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.getClipBounds(mRect);
            mCanvas.drawBitmap(mFrameBuffer, null, mRect, null);
            mCoreGame.getCurrentScene().drawing();
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void startGame() {
        if (mRunning) {
            return;
        }
        mRunning = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    public void stopGame() {
        if (!mRunning) {
            return;
        }
        mRunning = false;

        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
