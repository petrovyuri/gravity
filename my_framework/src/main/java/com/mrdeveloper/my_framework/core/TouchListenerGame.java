package com.mrdeveloper.my_framework.core;

import android.view.MotionEvent;
import android.view.View;

public class TouchListenerGame implements View.OnTouchListener {

    //region Fields
    private float mTouchX;
    private float mTouchY;

    private boolean mIsTouchDown;
    private boolean mIsTouchUp;

    private float mSceneWidth;
    private float mSceneHeight;
    //endregion

    public TouchListenerGame(View view, float sceneWidth, float sceneHeight) {
        view.setOnTouchListener(this);
        mSceneWidth = sceneWidth;
        mSceneHeight = sceneHeight;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        synchronized (this) {
            mIsTouchDown = false;
            mIsTouchUp = false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchX = event.getX() * mSceneWidth;
                    mTouchY = event.getY() * mSceneHeight;
                    mIsTouchDown = true;
                    mIsTouchUp = false;
                    break;
                case MotionEvent.ACTION_UP:
                    mTouchX = event.getX() * mSceneWidth;
                    mTouchY = event.getY() * mSceneHeight;
                    mIsTouchDown = false;
                    mIsTouchUp = true;
                    break;
            }
        }
        return true;
    }

    public boolean getTouchUp(int x, int y, int touchWidth, int touchHeight) {
        if (mIsTouchUp) {
            if (mTouchX >= x && mTouchX <= x + touchWidth - 1 && mTouchY <= y && mTouchY >= y - (touchHeight - 1)) {
                mIsTouchUp = false;
                return true;
            }
        }
        return false;
    }

    public boolean getTouchDown(int x, int y, int touchWidth, int touchHeight) {
        if (mIsTouchDown) {
            if (mTouchX >= x && mTouchX <= x + touchWidth - 1 && mTouchY <= y && mTouchY >= y - (touchHeight - 1)) {
                mIsTouchDown = false;
                return true;
            }
        }
        return false;
    }
}
