package com.mrdeveloper.my_framework.core;

import android.media.SoundPool;

public class SoundGame {
    private int mSound;
    private SoundPool mSoundPool;

    public SoundGame(int sound, SoundPool soundPool) {
        this.mSound = sound;
        this.mSoundPool = soundPool;
    }
    public void  play(float volume){
        mSoundPool.play(mSound,volume,volume,0,0,1);
    }
    public void dispose(){
        mSoundPool.unload(mSound);
    }
}
