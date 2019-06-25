package com.mrdeveloper.gravity.utilits;


import android.graphics.Bitmap;
import android.graphics.Typeface;

import com.mrdeveloper.my_framework.core.MusicGame;
import com.mrdeveloper.my_framework.core.SoundGame;

import java.util.ArrayList;

public class ResourceGame {
    //Обьявление ресурсов, для доступа к ним из любого места игры
    public static Bitmap sTextureAtlas;
    public static ArrayList<Bitmap> sSpritePlayer;
    public static ArrayList<Bitmap> sSpritePlayerBoost;
    public static ArrayList<Bitmap> sSpriteEnemy;
    public static ArrayList<Bitmap> sSpritePlayerShieldsOn;
    public static ArrayList<Bitmap> sSpritePlayerShieldsOnBoost;
    public static ArrayList<Bitmap> sSpriteProtector;
    public static Bitmap sShieldHitEnemy;
    public static ArrayList<Bitmap> sSpriteExplosionPlayer;
    public static MusicGame sMainMusicGame;
    public static SoundGame sSoundHit;
    public static SoundGame sSoundExplode;
    public static SoundGame sSoundTouch;
    public static Typeface mainMenuFont;
}
