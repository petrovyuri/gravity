package com.mrdeveloper.gravity.objects;

import com.mrdeveloper.my_framework.ObjectFW;
import com.mrdeveloper.my_framework.utilits.UtilRandomFW;

public class Star extends ObjectFW {

    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        this.speed = 2;
        this.x = UtilRandomFW.getCasualNumber(maxScreenX);;
        this.y = UtilRandomFW.getGap(minScreenY,maxScreenY);
    }

    public void update (double speedPlayer){
        x-=speedPlayer;
        x-=speed;
        if (x<0){
            x=maxScreenX;
            y = UtilRandomFW.getGap(minScreenY,maxScreenY);
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
