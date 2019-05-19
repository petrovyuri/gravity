package com.mrdeveloper.gravity.utilits;

import android.content.SharedPreferences;

import com.mrdeveloper.my_framework.core.CoreGame;

import java.util.ArrayList;

public class SettingsGame {

    public static int[] mDistance = {9999,5555,4444,3333,1111};

    public static void saveSettings(CoreGame coreGame){
        SharedPreferences.Editor editor = coreGame.getSharedPreferences().edit();
        editor.clear();
        for (int i = 0; i < 5; i++) {
            editor.putInt("passedDistance"+i, mDistance[i]);
        }
        editor.apply();
    }

    public static void loadSettings(CoreGame coreGame) {
        for (int i = 0; i < 5; i++) {
            mDistance[i] = coreGame
                    .getSharedPreferences()
                    .getInt("passedDistance" + i, mDistance[i]);
        }
    }

    public static void addDistance(int values) {
        for (int i = 0; i < 5; i++) {
            if (mDistance[i] < values) {
                mDistance[i] = values;
                break;
            }
        }
    }
}
