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

    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;

    private LoopGame loopGame;
    private GraphicsGame graphicsGame;
    private TouchListenerGame touchListenerGame;

    private AudioGame audioGame;
    private Display display;
    private Point sizeDisplay;
    private Bitmap frameBuffer;
    private SceneGame sceneGame;
    private float sceneWidth;
    private float sceneHeight;
    private boolean stateOnPause;
    private boolean stateOnResume;
    private SharedPreferences sharedPreferences;
    private final String SETTINGS = "settings";

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sizeDisplay = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);
        frameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);
        sceneWidth = FRAME_BUFFER_WIDTH / sizeDisplay.x;
        sceneHeight = FRAME_BUFFER_HEIGHT / sizeDisplay.y;
        audioGame = new AudioGame(this);
        loopGame = new LoopGame(this, frameBuffer);
        graphicsGame = new GraphicsGame(getAssets(), frameBuffer);
        touchListenerGame = new TouchListenerGame(loopGame, sceneWidth, sceneHeight);

        sceneGame = getStartScene();

        stateOnPause = false;
        stateOnResume = false;
        setContentView(loopGame);
    }

    public CoreGame() {

    }

    public AudioGame getAudioFW() {
        return audioGame;
    }

    public void onResume() {
        super.onResume();
        sceneGame.resume();
        loopGame.startGame();
    }

    public void onPause() {
        super.onPause();
        sceneGame.pause();
        loopGame.stopGame();
        stateOnPause = true;
        if (isFinishing()) {
            sceneGame.dispose();
        }
    }

    public GraphicsGame getGraphicsFW() {
        return graphicsGame;
    }

    public TouchListenerGame getTouchListenerFW() {
        return touchListenerGame;
    }

    public void setScene(SceneGame sceneGame) {
        if (sceneGame == null) {
            throw new IllegalArgumentException("Не возможно загрзуить сцену");
        }
        this.sceneGame.pause();
        this.sceneGame.dispose();
        sceneGame.resume();
        sceneGame.update();
        this.sceneGame = sceneGame;
    }

    public SceneGame getCurrentScene() {
        return sceneGame;
    }

    public SceneGame getStartScene() {
        return sceneGame;
    }


}
