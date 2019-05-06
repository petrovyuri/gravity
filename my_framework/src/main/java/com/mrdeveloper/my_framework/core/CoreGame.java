package com.mrdeveloper.my_framework.core;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;

public class CoreGame extends AppCompatActivity {

    //region Fields
    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;

    private LoopGame mLoopGame;
    private GraphicsGame mGraphicsGame;
    private TouchListenerGame mTouchListenerGame;

    private AudioGame mAudioGame;
    private Display mDisplay;
    private Point mSizeDisplay;
    private Bitmap mFrameBuffer;
    private SceneGame mSceneGame;
    private float mSceneWidth;
    private float mSceneHeight;
    private SharedPreferences sharedPreferences;
    private final String SETTINGS = "settings";
    //endregion


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        init();
        setContentView(mLoopGame);
    }

    private void init() {
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        mSizeDisplay = new Point();
        mDisplay = getWindowManager().getDefaultDisplay();
        mDisplay.getSize(mSizeDisplay);
        mFrameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);
        mSceneWidth = FRAME_BUFFER_WIDTH / mSizeDisplay.x;
        mSceneHeight = FRAME_BUFFER_HEIGHT / mSizeDisplay.y;
        mAudioGame = new AudioGame(this);
        mLoopGame = new LoopGame(this, mFrameBuffer);
        mGraphicsGame = new GraphicsGame(getAssets(), mFrameBuffer);
        mTouchListenerGame = new TouchListenerGame(mLoopGame, mSceneWidth, mSceneHeight);
    }

    public void start(SceneGame sceneGame) {
        mSceneGame = sceneGame;
    }

    public void onResume() {
        super.onResume();
        mSceneGame.resume();
        mLoopGame.startGame();
    }

    public void onPause() {
        super.onPause();
        mSceneGame.pause();
        mLoopGame.stopGame();
        if (isFinishing()) {
            mSceneGame.dispose();
        }
    }

    //region Get&Set
    public GraphicsGame getGraphicsFW() {
        return mGraphicsGame;
    }

    public TouchListenerGame getTouchListenerFW() {
        return mTouchListenerGame;
    }

    public void setScene(SceneGame sceneGame) {
        if (sceneGame == null) {
            throw new IllegalArgumentException("Не возможно загрзуить сцену");
        }
        this.mSceneGame.pause();
        this.mSceneGame.dispose();
        sceneGame.resume();
        sceneGame.update();
        this.mSceneGame = sceneGame;
    }

    public SceneGame getCurrentScene() {
        return mSceneGame;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public AudioGame getAudioFW() {
        return mAudioGame;
    }
    //endregion


}
