package com.rafael.bomberman;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by root on 31/12/16.
 */

public class Player extends GameObject {
    private int id;
    private Bitmap spritesheet;
    private int numBombas;
    private boolean vivo;
    private Animation animation;
    private long startTime;
    private int direccion;

    private int speed;

    private int xFinal, yFinal;

    public Player(int id, Bitmap res, int x, int y, int numFrames){
        this.id = id;
        this.x = x;
        this.y = y;
        speed = 5;

        this.vivo = true;
        this.spritesheet = res;

        this.width = 16;
        this.height = 16;
        animation = new Animation();

        Bitmap[] image = new Bitmap[numFrames];

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        direccion = Animation.S;
        animation.setFrame(direccion);

        startTime = System.nanoTime();
    }

    public void update(){
    }

    public void draw(Canvas canvas){
        if(vivo){
            canvas.drawBitmap(animation.getImage(), x, y, null);
        }

    }

    public int getSpeed()
    {
        return this.speed;
    }

    public void improveSpeed()
    {
        this.speed++;
    }

    public boolean isVivo(){
        return this.vivo;
    }

    public void setxFinal(int x){
        this.xFinal = x;
    }

    public int getxFinal(){
        return this.xFinal;
    }

    public void setyFinal(int y)
    {
        this.yFinal = y;
    }

    public int getyFinal(){
        return this.yFinal;
    }
}
