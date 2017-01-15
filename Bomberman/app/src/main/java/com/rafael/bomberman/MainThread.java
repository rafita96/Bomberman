package com.rafael.bomberman;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by root on 21/12/16.
 */

public class MainThread extends Thread {
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GameSurface gameSurface;
    private boolean running;
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GameSurface gameSurface)
    {
        super();

        this.surfaceHolder = surfaceHolder;
        this.gameSurface = gameSurface;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;

        while (running){
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            }catch (Exception e){ e.printStackTrace(); }
            finally {
                if(canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){ e.printStackTrace(); }
                }
            }

            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime-timeMillis;

            if(waitTime > 0){
                try {
                    this.sleep(waitTime);
                }catch (Exception e){ e.printStackTrace(); }
            }

            totalTime += System.nanoTime()-startTime;
            frameCount++;

            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                totalTime = 0;
                frameCount = 0;

                }
            }
        }

    public void startRunning()
    {
        this.running = true;
    }

    public void stopRunning()
    {
        this.running = false;
    }

}
