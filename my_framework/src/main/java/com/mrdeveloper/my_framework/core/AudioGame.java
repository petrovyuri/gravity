package com.mrdeveloper.my_framework.core;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class AudioGame {

    private AssetManager mAssetManager;
    private SoundPool mSoundPool;

    public AudioGame(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mAssetManager = activity.getAssets();
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
    }

    public MusicGame newMusic(String fileName) {
        try {
            AssetFileDescriptor assetFileDescriptor = mAssetManager.openFd(fileName);
            return new MusicGame(assetFileDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Не возможно загрузить музыку");
        }
    }

    public SoundGame newSound(String fileName) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sound = mSoundPool.load(assetFileDescriptor, 0);
        return new SoundGame(sound, mSoundPool);
    }
}
