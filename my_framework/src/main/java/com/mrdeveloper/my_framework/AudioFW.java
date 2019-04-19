package com.mrdeveloper.my_framework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;

import java.io.IOException;

public class AudioFW {
    AssetManager assetManager;

    public AudioFW(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager=activity.getAssets();
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
}
