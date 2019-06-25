package com.mrdeveloper.gravity.generators;

import com.mrdeveloper.gravity.objects.Enemy;
import com.mrdeveloper.my_framework.core.GraphicsGame;

import java.util.ArrayList;

/*  Класс генерирует всех врагов в игре */
public class GeneratorEnemy {

    //region Fields
    private int mMaxScreenY;
    private int mMaxScreenX;
    private int mMinScreenY;
    private ArrayList<Enemy> mEnemyArrayList;
    private int mTypeEnemy;

    //endregion

    //region Main
    public GeneratorEnemy(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.mMaxScreenX = sceneWidth;
        this.mMaxScreenY = sceneHeight;
        this.mMinScreenY = minScreenY;
        mEnemyArrayList = new ArrayList<>();
    }

    public void update(double speedPlayer) {
        if (mEnemyArrayList.size() < 3) {
            addEnemy(1);
        }
        for (int i = 0; i < mEnemyArrayList.size(); i++) {
            mEnemyArrayList.get(i).update(speedPlayer);
        }
    }

    public void drawing(GraphicsGame graphicsGame) {
        for (int i = 0; i < mEnemyArrayList.size(); i++) {
            mEnemyArrayList.get(i).drawing(graphicsGame);
        }
    }
    //endregion

    //region Methods
    private void addEnemy(int amountEnemy) {
        for (int i = 0; i < amountEnemy; i++) {
            mEnemyArrayList.add(new Enemy(mMaxScreenX, mMaxScreenY, mMinScreenY, 1));
        }
    }
    private void addEnemy(int amountEnemy,int typeEnemy) {
        for (int i = 0; i < amountEnemy; i++) {
            mEnemyArrayList.add(new Enemy(mMaxScreenX, mMaxScreenY, mMinScreenY, 1));
        }
    }

    public void hitPlayer(Enemy enemy) {
        for (int i = 0; i < mEnemyArrayList.size(); i++) {
            mEnemyArrayList.remove(enemy);
        }
    }
    //endregion

    //region Get&Set
    public ArrayList<Enemy> getEnemyArrayList() {
        return mEnemyArrayList;
    }
    //endregion
}
