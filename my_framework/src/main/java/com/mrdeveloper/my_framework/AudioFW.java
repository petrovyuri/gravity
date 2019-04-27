package com.mrdeveloper.my_framework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

public class AudioFW {
    AssetManager assetManager;
    SoundPool soundPool;

    public AudioFW(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager=activity.getAssets();
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        } else soundPool = new SoundPool(6,AudioManager.STREAM_MUSIC,0);
    }

    public MusicFW newMusic(String fileName){
        AssetFileDescriptor assetFileDescriptor= null;
        try {
            assetFileDescriptor = assetManager.openFd(fileName);
            return new MusicFW(assetFileDescriptor);
        } catch (IOException e) {
            throw  new RuntimeException("Не возможно загрузить музыку");
        }
    }
    public SoundFW newSound(String fileName){
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor=assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sound = soundPool.load(assetFileDescriptor,0);
        return new SoundFW(sound,soundPool);
    }
}
