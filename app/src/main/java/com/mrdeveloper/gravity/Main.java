package com.mrdeveloper.gravity;

import com.mrdeveloper.gravity.scenes.LoaderResourceScene;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class Main extends CoreGame {

    public SceneGame getStartScene() {
        return new LoaderResourceScene(this);
    }

}
