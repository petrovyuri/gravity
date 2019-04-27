package com.mrdeveloper.my_framework;

import android.media.SoundPool;

public class SoundFW {
    int sound;
    SoundPool soundPool;

    public SoundFW(int sound, SoundPool soundPool) {
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
