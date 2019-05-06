package com.mrdeveloper.my_framework.utilits;

import com.mrdeveloper.my_framework.core.ObjectGame;

public class UtilCollisionDetectGame {

    static double object1Y;

    static double temp;

    static double object2X;
    static double object2Y;

    static double radiusObject1;
    static double radiusObject2;

    static double dx;
    static double dy;

    static double distanceObjects;

    public static boolean collisionDetect(ObjectGame object1, ObjectGame object2){

       double object1X=object1.getHitBox().centerX();
        object1Y=object1.getHitBox().centerY();

        object2X=object2.getHitBox().centerX();
        object2Y=object2.getHitBox().centerY();

        radiusObject1=object1.getRadius();
        radiusObject2=object2.getRadius();

        dx=object1X-object2X;
        dy=object1Y-object2Y;

        distanceObjects=Math.sqrt(dx*dx+dy*dy);

        return distanceObjects < (radiusObject1 + radiusObject2);
    }

}
