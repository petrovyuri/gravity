package com.mrdeveloper.my_framework.core;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class AudioGame {
    AssetManager assetManager;
    SoundPool soundPool;

    public AudioGame(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager=activity.getAssets();
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
    }

    public MusicGame newMusic(String fileName){
        try {
          AssetFileDescriptor  assetFileDescriptor = assetManager.openFd(fileName);
            return new MusicGame(assetFileDescriptor);
        } catch (IOException e) {
            throw  new RuntimeException("Не возможно загрузить музыку");
        }
    }
    public SoundGame newSound(String fileName){
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor=assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sound = soundPool.load(assetFileDescriptor,0);
        return new SoundGame(sound,soundPool);
    }
}
