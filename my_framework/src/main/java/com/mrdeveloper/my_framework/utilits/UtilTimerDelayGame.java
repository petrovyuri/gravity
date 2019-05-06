package com.mrdeveloper.my_framework.utilits;

public class UtilTimerDelayGame {
    double startTime;
    double nowTime;
    double elapsedTime;
    final double SECOND = 1000000000;

    public  void startTimer(){
        startTime = System.nanoTime()/SECOND;
    }

    public boolean timerDelay(double second){
        nowTime = System.nanoTime()/SECOND;
        elapsedTime = nowTime - startTime;
        if (elapsedTime>second){
            return true;
        }
        return false;
    }
}
