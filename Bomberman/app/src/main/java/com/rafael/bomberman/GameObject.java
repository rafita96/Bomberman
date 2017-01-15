package com.rafael.bomberman;

import android.content.Context;
import android.graphics.Rect;

/**
 * Created by root on 31/12/16.
 */

public abstract class GameObject{
    protected float x;
    protected float y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;
    protected Context mContext;

    public void setX(float x){
        this.x = x;
    }

    public float getX(){
        return this.x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getY(){
        return this.y;
    }

    public void setDx(int dx){
        this.dx = dx;
    }

    public int getDx(){
        return this.dx;
    }

    public void setDy(int dy){
        this.dy = dy;
    }

    public int getDy(){
        return this.dy;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getWidth(){
        return this.width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return this.height;
    }

    public Rect getRectangle(){
        return new Rect((int)x, (int)y, (int)x+width, (int)y+height);
    }
}
