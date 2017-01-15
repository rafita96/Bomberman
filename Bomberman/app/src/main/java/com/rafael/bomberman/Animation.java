package com.rafael.bomberman;

import android.graphics.Bitmap;
import android.provider.Settings;

/**
 * Created by root on 31/12/16.
 */

public class Animation {

    public static final int W = 0;
    public static final int N = 1;
    public static final int E = 2;
    public static final int S = 3;

    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playedOnce;

    public void setFrames(Bitmap[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long delay){
        this.delay = delay;
    }

    public void setFrame(int i){
        this.currentFrame = i;
    }

    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;

        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }

        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public Bitmap getImage(){
        return frames[currentFrame];
    }

    public int getFrame()
    {
        return currentFrame;
    }

    public boolean isPlayedOnce(){
        return this.playedOnce;
    }
}
