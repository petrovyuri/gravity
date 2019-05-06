package com.mrdeveloper.my_framework.core;

import android.graphics.Bitmap;

public class AnimationGame {

    //region Fields
    private double mSpeedAnimation;
    private int mDelayIndex;
    private int mCountFrames;
    private int mFrames;

    private Bitmap mSprite;
    private Bitmap mSprite1;
    private Bitmap mSprite2;
    private Bitmap mSprite3;
    private Bitmap mSprite4;
    //endregion

    public AnimationGame(double speedAnimation,
                         Bitmap sprite1,
                         Bitmap sprite2,
                         Bitmap sprite3,
                         Bitmap sprite4) {

        mSprite = sprite1;
        mSprite1 = sprite1;
        mSprite2 = sprite2;
        mSprite3 = sprite3;
        mSprite4 = sprite4;
        mSpeedAnimation = speedAnimation;
        mFrames = 4;
    }

    public void runAnimation() {
        mDelayIndex++;
        if (mDelayIndex > mSpeedAnimation) {
            mDelayIndex = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        if (mCountFrames == 0) {
            mSprite = mSprite1;
        }
        if (mCountFrames == 1) {
            mSprite = mSprite2;
        }
        if (mCountFrames == 2) {
            mSprite = mSprite3;
        }
        if (mCountFrames == 3) {
            mSprite = mSprite4;
        }
        mCountFrames++;
        if (mCountFrames > mFrames) {
            mCountFrames = 0;
        }
    }

    public void drawingAnimation(GraphicsGame graphicsGame, int x, int y) {
        graphicsGame.drawTexture(mSprite, x, y);
    }

}
