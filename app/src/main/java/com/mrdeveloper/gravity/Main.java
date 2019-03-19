package com.mrdeveloper.gravity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mrdeveloper.gravity.clases.LoaderAssets;
import com.mrdeveloper.gravity.scenes.MainMenuScene;
import com.mrdeveloper.my_framework.CoreFW;
import com.mrdeveloper.my_framework.LoopFW;
import com.mrdeveloper.my_framework.SceneFW;

public class Main extends CoreFW {

    public SceneFW getStartScene() {
        LoaderAssets loaderAssets = new LoaderAssets(this,this.getGraphicsFW());
        return new MainMenuScene(this);

    }
}
