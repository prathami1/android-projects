package com.example.atari;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation") public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    TimerTask timertask;
    AnimatedView animatedView = null;
    ShapeDrawable mDrawable = new ShapeDrawable();
    ShapeDrawable oDrawable = new ShapeDrawable();
    public static int x;
    public static int y;
    int count = 0, hitCount = 0, points = 0;
    boolean hit = false;
    Canvas globalCanvas;

    public int[] oStart = {0, 100, 200, 300, 400, 500, 600, 700, 800};

    int width = 100, height = 400, top = 0, bottom = top + height;
    int position = (int)(Math.random()*8);
    int left = oStart[position];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        y = 2020;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        long lastUpdate = System.currentTimeMillis();

        TimerMethod();
        Timer time = new Timer();
        time.schedule(timertask, 1, 1000);

        animatedView = new AnimatedView(this);
        setContentView(animatedView);
        PlayBackgroundSound(animatedView);

        new Timer().scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                top += 10;
                if(top > 2020)
                {
                    top = 0;
                    if(!hit)
                        points++;
                    hit = false;
                    position = (int)(Math.random()*8);
                    left = oStart[position];
                }
            }
        }, 0, 10);
    }

    public void PlayBackgroundSound(View view)
    {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
    }

    public void PlaySoundEffect()
    {
        Intent intent = new Intent(MainActivity.this, SoundEffectService.class);
        startService(intent);
    }

    public void TimerMethod()
    {
        timertask = new TimerTask()
        {
            @Override
            public void run()
            {
                count++;
                hitCount--;
                if(count == 60)
                {
                    this.cancel();
                }
            }
        };
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) { }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            x -= ((int) event.values[0])*2;
            // y += (int) event.values[1];

            if(x < 0)
                x = 0;
            else if(x > 970)
                x = 970;
        }
    }

    public void addObstacle()
    {
        oDrawable = new ShapeDrawable(new RectShape());
        oDrawable.getPaint().setColor(Color.RED);
        oDrawable.setBounds(left, top, left + width, top + height);
    }

    public class AnimatedView extends androidx.appcompat.widget.AppCompatImageView
    {
        static final int width = 100;
        static final int height = 100;

        public AnimatedView(Context context)
        {
            super(context);
            mDrawable = new ShapeDrawable(new OvalShape());
            mDrawable.getPaint().setColor(0xffffAC23);
            mDrawable.setBounds(x, y, x + width, y + height);
            addObstacle();
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(60);

            if(hitCount == 0)
            {
                mDrawable.getPaint().setColor(0xffffAC23);
            }

            canvas.drawText("Points: " + String.valueOf(points), 10, 100, paint);

            if(count < 60)
            { canvas.drawText("Time Left: " + String.valueOf(60-count), 700, 100, paint); }
            else
            {
                animatedView.setVisibility(View.INVISIBLE);
                setContentView(R.layout.activity_main);
            }

            mDrawable.setBounds(x, y, x + width, y + height);
            mDrawable.draw(canvas);

            oDrawable.setBounds(left, top, left + width, top + height);
            oDrawable.draw(canvas);

            //if(x - (left + width) < 200 || (left + width) - x < 200 && y == (top + height))
            if(x <= (left + width) && left <= (x + width) && y <= top + height && top <= y + height)
            {
                mDrawable.getPaint().setColor(Color.GRAY);
                hitCount = 5;
                hit = true;
                PlaySoundEffect();
            }

            invalidate();
        }
    }

}

