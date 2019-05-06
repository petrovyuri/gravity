package com.mrdeveloper.my_framework.core;

import android.graphics.Rect;

public abstract class ObjectGame {

    //region Fields
    protected int pMaxScreenX;
    protected int pMaxScreenY;
    protected int pMinScreenX;
    protected int pMinScreenY;
    protected int pX;
    protected int pY;
    protected double pSpeed;

    protected Rect pHitBox;
    protected double pRadius;
    //endregion

    //region Get&Set
    public int getMaxScreenX() {
        return pMaxScreenX;
    }

    public void setMaxScreenX(int maxScreenX) {
        this.pMaxScreenX = maxScreenX;
    }

    public int getMaxScreenY() {
        return pMaxScreenY;
    }

    public void setMaxScreenY(int maxScreenY) {
        this.pMaxScreenY = maxScreenY;
    }

    public int getMinScreenX() {
        return pMinScreenX;
    }

    public void setMinScreenX(int minScreenX) {
        this.pMinScreenX = minScreenX;
    }

    public int getMinScreenY() {
        return pMinScreenY;
    }

    public void setMinScreenY(int minScreenY) {
        this.pMinScreenY = minScreenY;
    }

    public int getX() {
        return pX;
    }

    public void setX(int x) {
        this.pX = x;
    }

    public int getY() {
        return pY;
    }

    public void setY(int y) {
        this.pY = y;
    }

    public double getSpeed() {
        return pSpeed;
    }

    public void setSpeed(double pSpeed) {
        this.pSpeed = pSpeed;
    }

    public Rect getHitBox() {
        return pHitBox;
    }

    public double getRadius() {
        return pRadius;
    }
    //endregion

}
