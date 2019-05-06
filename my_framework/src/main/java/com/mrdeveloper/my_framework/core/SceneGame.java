package com.mrdeveloper.my_framework.core;

public abstract class SceneGame {

    protected CoreGame pCoreGame;
    protected int pSceneWidth;
    protected int pSceneHeight;
    protected GraphicsGame pGraphicsGame;

    protected SceneGame(CoreGame coreGame) {
        pCoreGame = coreGame;
        pSceneWidth = coreGame.getGraphicsFW().getWidthFrameBuffer();
        pSceneHeight = coreGame.getGraphicsFW().getHeightFrameBuffer();
        pGraphicsGame = coreGame.getGraphicsFW();

    }
    protected abstract void update();
    protected abstract void drawing();
    protected abstract void pause();
    protected abstract void resume();
    protected abstract void dispose();
}
