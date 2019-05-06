package com.mrdeveloper.my_framework.core;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MusicGame {

    private MediaPlayer mMediaPlayer;
    private boolean mIsPrepared;

    public MusicGame(AssetFileDescriptor assetFileDescriptor) {
        mIsPrepared = false;
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mIsPrepared = true;
    }

    public void play(boolean looping, float volume) {
        if (mMediaPlayer.isPlaying()) {
            return;
        }
        synchronized (this) {
            if (!mIsPrepared) {
                try {
                    mMediaPlayer.prepare();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mMediaPlayer.setLooping(looping);
            mMediaPlayer.setVolume(volume, volume);
            mMediaPlayer.start();
        }
    }

    public void stop() {
        mMediaPlayer.stop();
        synchronized (this) {
            mIsPrepared = false;
        }
    }

    public void dispose() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}
