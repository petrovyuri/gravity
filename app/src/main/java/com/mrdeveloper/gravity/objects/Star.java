package com.mrdeveloper.gravity.objects;

import com.mrdeveloper.my_framework.core.ObjectGame;
import com.mrdeveloper.my_framework.utilits.UtilRandomGame;

public class Star extends ObjectGame {

    //region Main methods
    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    public void update (double speedPlayer){
        x-=speedPlayer;
        x-=speed;
        if (x<0){
            x=maxScreenX;
            y = UtilRandomGame.getGap(minScreenY,maxScreenY);
        }
    }
    //endregion

    //region Methods
    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        this.speed = 2;
        this.x = UtilRandomGame.getCasualNumber(maxScreenX);
        this.y = UtilRandomGame.getGap(minScreenY,maxScreenY);
    }
    //endregion

    //region Get&Set
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    //endregion
}
