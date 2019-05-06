package com.mrdeveloper.my_framework.core;

import android.graphics.Bitmap;

public class AnimationGame {

    double speedAnimation;
    int delayIndex;
    int countFrames;
    int frames;

    Bitmap sprite;
    Bitmap sprite1;
    Bitmap sprite2;
    Bitmap sprite3;
    Bitmap sprite4;

    public AnimationGame(double speedAnimation,
                         Bitmap sprite1,
                         Bitmap sprite2,
                         Bitmap sprite3,
                         Bitmap sprite4) {

        sprite = sprite1;
        this.sprite1 = sprite1;
        this.sprite2 = sprite2;
        this.sprite3 = sprite3;
        this.sprite4 = sprite4;
        this.speedAnimation = speedAnimation;
        frames = 4;
    }

    public void runAnimation(){
        delayIndex++;
        if (delayIndex>speedAnimation){
            delayIndex=0;
            nextFrame();
        }
    }

    private void nextFrame() {
        if (countFrames==0){
            sprite=sprite1;
        }
        if (countFrames==1){
            sprite=sprite2;
        }
        if (countFrames==2){
            sprite=sprite3;
        }
        if (countFrames==3){
            sprite=sprite4;
        }
        countFrames++;
        if (countFrames>frames){
            countFrames=0;
        }
    }

    public void drawingAnimation(GraphicsGame graphicsGame, int x, int y ){
        graphicsGame.drawTexture(sprite, x,y);
    }

}
