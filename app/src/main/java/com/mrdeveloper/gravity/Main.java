package com.mrdeveloper.gravity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mrdeveloper.gravity.clases.LoaderAssets;
import com.mrdeveloper.gravity.scenes.MainMenuScene;
import com.mrdeveloper.my_framework.core.CoreGame;

public class Main extends CoreGame {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoaderAssets loaderAssets = new LoaderAssets(this,this.getGraphicsFW());
        this.start(new MainMenuScene(this));
    }

}
