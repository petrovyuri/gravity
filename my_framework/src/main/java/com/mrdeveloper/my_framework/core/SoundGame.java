package com.mrdeveloper.my_framework.core;

import android.media.SoundPool;

public class SoundGame {
    int sound;
    SoundPool soundPool;

    public SoundGame(int sound, SoundPool soundPool) {
        this.sound = sound;
        this.soundPool = soundPool;
    }
    public void  play(float volume){
        soundPool.play(sound,volume,volume,0,0,1);
    }
    public void dispose(){
        soundPool.unload(sound);
    }
}
