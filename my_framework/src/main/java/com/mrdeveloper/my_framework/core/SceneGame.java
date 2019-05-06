package com.mrdeveloper.my_framework.core;

public abstract class SceneGame {

    public CoreGame coreGame;
    public int sceneWidth;
    public int sceneHeight;
    public GraphicsGame graphicsGame;

    public SceneGame(CoreGame coreGame) {
        this.coreGame = coreGame;
        sceneWidth= coreGame.getGraphicsFW().getWidthFrameBuffer();
        sceneHeight = coreGame.getGraphicsFW().getHeightFrameBuffer();
        graphicsGame = coreGame.getGraphicsFW();

    }
    public abstract void update();
    public abstract void drawing();
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}
