package com.example.atari;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;
public class BackgroundSoundService extends Service
{
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy()
    {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onLowMemory() { }
}
