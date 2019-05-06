package com.mrdeveloper.my_framework.utilits;

public class UtilTimerDelayGame {

    private double mStartTime;
    private double mNowTime;
    private double mElapsedTime;
    private final double SECOND = 1000000000;

    public void startTimer() {
        mStartTime = System.nanoTime() / SECOND;
    }

    public boolean timerDelay(double second) {
        mNowTime = System.nanoTime() / SECOND;
        mElapsedTime = mNowTime - mStartTime;
        return mElapsedTime > second;
    }
}
