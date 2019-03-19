package com.mrdeveloper.gravity.generators;

import com.mrdeveloper.gravity.objects.Enemy;
import com.mrdeveloper.my_framework.GraphicsFW;

import java.util.ArrayList;

public class GeneratorEnemy {
    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;

   public ArrayList<Enemy> enemyArrayList;

    public GeneratorEnemy(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        enemyArrayList = new ArrayList<>();
    }

    public void update (double speedPlayer){

        if (enemyArrayList.size()<3){
            addEnemy(speedPlayer,3);
        }

        for (int i = 0; i < enemyArrayList.size() ; i++) {
            enemyArrayList.get(i).update(speedPlayer);
        }
    }

    private void addEnemy(double speedPlayer, int ammountEnemy) {
        for (int i = 0; i < ammountEnemy ; i++) {
            enemyArrayList.add(new Enemy(maxScreenX,maxScreenY,minScreenY,1));
        }
    }

    public void drawing(GraphicsFW graphicsFW){
        for (int i = 0; i < enemyArrayList.size() ; i++) {
            enemyArrayList.get(i).drawing(graphicsFW);
        }
    }

    public void hitPlayer(Enemy enemy) {
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.remove(enemy);
        }
    }
}
