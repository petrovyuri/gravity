package com.mrdeveloper.my_framework.core;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
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
    private SharedPreferences mSharedPreferences;
    private final String SETTINGS = "settings";
    private boolean mIsPressedKeyBack;
//endregion


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        init();
        setContentView(mLoopGame);
    }

    private void init() {
        mSharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
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
        mSceneGame = getStartScene();
        mIsPressedKeyBack = false;
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mIsPressedKeyBack = true;
            return true;
        }
        return false;
    }

    //region Get&Set
    public boolean isPressedKeyBack() {
        return mIsPressedKeyBack;
    }

    public void setPressedKeyBack(boolean mIsPressedKeyBack) {
        this.mIsPressedKeyBack = mIsPressedKeyBack;
    }

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

    public SceneGame getStartScene() {
        return mSceneGame;
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public AudioGame getAudioFW() {
        return mAudioGame;
    }
    //endregion


}
