package com.mrdeveloper.my_framework.utilits;

import java.util.Random;

public class UtilRandomFW {
    public static int getCasualNumber(int number){
        Random random = new Random();
        int casualNumber;
        casualNumber=random.nextInt(number);
        return casualNumber;
    }

    public static int getGap(int minNumber, int maxNumber){
        int gap=0;
        gap = (int) ((Math.random()*++maxNumber)+minNumber);
        return gap;
    }
}
