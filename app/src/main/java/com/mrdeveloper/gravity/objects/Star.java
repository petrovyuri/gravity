package com.mrdeveloper.gravity.objects;

import com.mrdeveloper.my_framework.core.ObjectGame;
import com.mrdeveloper.my_framework.utilits.UtilRandomGame;

public class Star extends ObjectGame {

    //region Main methods
    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    public void update (double speedPlayer){
        pX -=speedPlayer;
        pX -= pSpeed;
        if (pX <0){
            pX = pMaxScreenX;
            pY = UtilRandomGame.getGap(pMinScreenY, pMaxScreenY);
        }
    }
    //endregion

    //region Methods
    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.pMaxScreenX = sceneWidth;
        this.pMaxScreenY = sceneHeight;
        this.pMinScreenX = 0;
        this.pMinScreenY = minScreenY;
        this.pSpeed = 2;
        this.pX = UtilRandomGame.getCasualNumber(pMaxScreenX);
        this.pY = UtilRandomGame.getGap(minScreenY, pMaxScreenY);
    }
    //endregion

    //region Get&Set
    public int getX(){
        return pX;
    }
    public int getY(){
        return pY;
    }
    //endregion
}
