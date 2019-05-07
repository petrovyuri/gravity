package com.mrdeveloper.gravity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mrdeveloper.gravity.clases.LoaderAssets;
import com.mrdeveloper.gravity.scenes.MainMenuScene;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class Main extends CoreGame {

    public SceneGame getStartScene() {
        LoaderAssets loaderAssets = new LoaderAssets(this, this.getGraphicsFW());
        return new MainMenuScene(this);
    }

}
