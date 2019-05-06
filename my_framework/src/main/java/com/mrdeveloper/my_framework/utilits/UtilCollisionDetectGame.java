package com.mrdeveloper.my_framework.utilits;

import com.mrdeveloper.my_framework.core.ObjectGame;

public class UtilCollisionDetectGame {

    private static double sObject1X;
    private static double sObject1Y;
    private static double sObject2X;
    private static double sObject2Y;

    private static double sRadiusObject1;
    private static double sRadiusObject2;

    private static double sDx;
    private static double sDy;

    private static double sDistanceObjects;

    public static boolean collisionDetect(ObjectGame object1, ObjectGame object2) {

        sObject1X = object1.getHitBox().centerX();
        sObject1Y = object1.getHitBox().centerY();

        sObject2X = object2.getHitBox().centerX();
        sObject2Y = object2.getHitBox().centerY();

        sRadiusObject1 = object1.getRadius();
        sRadiusObject2 = object2.getRadius();

        sDx = sObject1X - sObject2X;
        sDy = sObject1Y - sObject2Y;

        sDistanceObjects = Math.sqrt(sDx * sDx + sDy * sDy);

        return sDistanceObjects < (sRadiusObject1 + sRadiusObject2);
    }

}
