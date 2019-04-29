package com.mrdeveloper.gravity.clases;

import com.mrdeveloper.gravity.generators.GeneratorBackground;
import com.mrdeveloper.gravity.generators.GeneratorEnemy;
import com.mrdeveloper.gravity.generators.GeneratorGifts;
import com.mrdeveloper.gravity.objects.HUD;
import com.mrdeveloper.gravity.objects.MainPlayer;
import com.mrdeveloper.gravity.utilits.UtilResource;
import com.mrdeveloper.my_framework.CollisionDetect;
import com.mrdeveloper.my_framework.CoreFW;
import com.mrdeveloper.my_framework.GraphicsFW;

public class GameManager {

    public final static double SPEED_ANIMATION = 3;

    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;

    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    public static boolean gameOver;

    public int getPassedDistance() {
        return passedDistance;
    }

    MainPlayer mainPlayer;
    GeneratorBackground generatorBackground;
    GeneratorEnemy generatorEnemy;
    GeneratorGifts generatorGifts;
    HUD hud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenY = hud.getHEIGHT_HUD();
        minScreenX = 0;
        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        generatorBackground = new GeneratorBackground(sceneWidth, sceneHeight, minScreenY);
        generatorGifts = new GeneratorGifts(sceneWidth, sceneHeight, minScreenY);
        generatorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight, minScreenY);
        gameOver = false;
    }

    public void update() {
        generatorBackground.update(mainPlayer.getSpeedPlayer());
        mainPlayer.update();
        generatorEnemy.update(mainPlayer.getSpeedPlayer());
        generatorGifts.update(mainPlayer.getSpeedPlayer());
        passedDistance += mainPlayer.getSpeedPlayer();
        currentSpeedPlayer = (int) mainPlayer.getSpeedPlayer() * 60;
        currentShieldsPlayer = mainPlayer.getShieldsPlayer();

        hud.update(passedDistance, currentSpeedPlayer, currentShieldsPlayer);

        checkHit();

    }

    private void checkHit() {
        for (int i = 0; i < generatorEnemy.enemyArrayList.size(); i++) {
            if (CollisionDetect.collisionDetect(mainPlayer, generatorEnemy.enemyArrayList.get(i))) {
                UtilResource.hit.play(1);
                mainPlayer.hitEnemy();
                generatorEnemy.hitPlayer(generatorEnemy.enemyArrayList.get(i));
            }
        }
        if (CollisionDetect.collisionDetect(mainPlayer,generatorGifts.getProtector())){
            hitPlayerWithProtector();
        }
    }

    private void hitPlayerWithProtector() {
        mainPlayer.hitProtector();
        generatorGifts.hitProtectorWithPlayer();
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
        generatorBackground.drawing(graphicsFW);
        generatorGifts.drawing(graphicsFW);
        generatorEnemy.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }

}
