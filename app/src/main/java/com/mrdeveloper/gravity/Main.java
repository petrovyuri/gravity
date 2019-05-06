package com.mrdeveloper.gravity;

import com.mrdeveloper.gravity.clases.LoaderAssets;
import com.mrdeveloper.gravity.scenes.MainMenuScene;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class Main extends CoreGame {

    public SceneGame getStartScene() {
        //TODO Сделать отдельный поток для loaderAssets AsyncTask
        LoaderAssets loaderAssets = new LoaderAssets(this,this.getGraphicsFW());
        return new MainMenuScene(this);
    }
}
