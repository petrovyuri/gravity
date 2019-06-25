package com.mrdeveloper.gravity.tasks;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.gravity.interfaces.TaskCompleteListener;
import com.mrdeveloper.gravity.scenes.LoaderResourceScene;
import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.GraphicsGame;

import java.util.ArrayList;

public class  LoaderTask extends AsyncTask<Void, Integer, Void> {

    private CoreGame mCoreGame;
    private TaskCompleteListener mTaskCompleteListener;

    public LoaderTask(CoreGame coreGame, TaskCompleteListener taskCompleteListener) {
        mCoreGame = coreGame;
        mTaskCompleteListener = taskCompleteListener;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        LoaderResourceScene.setProgressLoader(values[0]);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        loaderAssets();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTaskCompleteListener.onComplete();
    }

    private void loaderAssets() {
        loadTexture(mCoreGame.getGraphicsFW());
        publishProgress(100);
        loadSpritePlayer(mCoreGame.getGraphicsFW());
        publishProgress(300);

        loadSpriteEnemy(mCoreGame.getGraphicsFW());
        publishProgress(500);
        loadOther(mCoreGame.getGraphicsFW());
        publishProgress(600);
        loadAudio(mCoreGame);

        loadSpritePlayerShieldsOn(mCoreGame.getGraphicsFW());
        publishProgress(700);
        loadGifts(mCoreGame.getGraphicsFW());
        publishProgress(800);
    }

    private void loadGifts(GraphicsGame graphicsGame) {
        //Метод загружает подарки
        ResourceGame.sSpriteProtector = new ArrayList<>();
        ResourceGame.sSpriteProtector.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                256, 192, 32, 32));
        ResourceGame.sSpriteProtector.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                288, 192, 32, 32));
        ResourceGame.sSpriteProtector.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                320, 192, 32, 32));
        ResourceGame.sSpriteProtector.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                352, 192, 32, 32));
    }

    private void loadSpritePlayerShieldsOn(GraphicsGame graphicsGame) {
        //Метод загружает спрайты игрока с включенными щитами
        ResourceGame.sSpritePlayerShieldsOn = new ArrayList<>();
        ResourceGame.sSpritePlayerShieldsOnBoost = new ArrayList<>();
        ResourceGame.sSpritePlayerShieldsOn.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                0, 128, 64, 64));
        ResourceGame.sSpritePlayerShieldsOn.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                64, 128, 64, 64));
        ResourceGame.sSpritePlayerShieldsOn.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                128, 128, 64, 64));
        ResourceGame.sSpritePlayerShieldsOn.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                192, 128, 64, 64));

        ResourceGame.sSpritePlayerShieldsOnBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                0, 192, 64, 64));
        ResourceGame.sSpritePlayerShieldsOnBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                64, 192, 64, 64));
        ResourceGame.sSpritePlayerShieldsOnBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                128, 192, 64, 64));
        ResourceGame.sSpritePlayerShieldsOnBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                192, 192, 64, 64));
    }

    private void loadAudio(CoreGame coreGame) {
        //Мето загружает музыку и звуки
        ResourceGame.sMainMusicGame = coreGame.getAudioFW().newMusic("music.ogg");
        ResourceGame.sSoundHit = coreGame.getAudioFW().newSound("hit.ogg");
        ResourceGame.sSoundExplode = coreGame.getAudioFW().newSound("explode.ogg");
        ResourceGame.sSoundTouch = coreGame.getAudioFW().newSound("touch.ogg");
    }

    private void loadOther(GraphicsGame graphicsGame) {
        ResourceGame.sShieldHitEnemy = graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                0, 128, 64, 64);
        SettingsGame.loadSettings(mCoreGame);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ResourceGame.mainMenuFont = mCoreGame.getResources().getFont(R.font.russo_one);
        } else {
            ResourceGame.mainMenuFont = ResourcesCompat.getFont(mCoreGame.getApplicationContext(),R.font.russo_one);
        }
    }

    private void loadSpriteEnemy(GraphicsGame graphicsGame) {
        //Метод загружает спрайты врагов
        ResourceGame.sSpriteEnemy = new ArrayList<>();

        ResourceGame.sSpriteEnemy.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 256, 0,
                64, 64));
        ResourceGame.sSpriteEnemy.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 320, 0,
                64, 64));
        ResourceGame.sSpriteEnemy.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 384, 0,
                64, 64));
        ResourceGame.sSpriteEnemy.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 448, 0,
                64, 64));
    }

    private void loadSpritePlayer(GraphicsGame graphicsGame) {
        //Метод загружает спрайты игрока без щитов
        ResourceGame.sSpritePlayer = new ArrayList<>();
        ResourceGame.sSpritePlayerBoost = new ArrayList<>();
        ResourceGame.sSpriteExplosionPlayer = new ArrayList<>();

        ResourceGame.sSpriteExplosionPlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                256, 256, 64, 64));
        ResourceGame.sSpriteExplosionPlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                320, 256, 64, 64));
        ResourceGame.sSpriteExplosionPlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                384, 256, 64, 64));
        ResourceGame.sSpriteExplosionPlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                448, 256, 64, 64));


        ResourceGame.sSpritePlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 0, 0,
                64, 64));
        ResourceGame.sSpritePlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 64, 0,
                64, 64));
        ResourceGame.sSpritePlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 128, 0,
                64, 64));
        ResourceGame.sSpritePlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 192, 0,
                64, 64));

        ResourceGame.sSpritePlayerBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 0, 64,
                64, 64));
        ResourceGame.sSpritePlayerBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 64, 64,
                64, 64));
        ResourceGame.sSpritePlayerBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 128, 64,
                64, 64));
        ResourceGame.sSpritePlayerBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 192, 64,
                64, 64));

    }

    private void loadTexture(GraphicsGame graphicsGame) {
        //Загрузка текстур
        ResourceGame.sTextureAtlas = graphicsGame.newTexture("texture_atlas.png");
    }

}
