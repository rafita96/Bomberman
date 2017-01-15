package com.rafael.bomberman;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 31/12/16.
 */

public class Cuadro extends GameObject{
    public static final int WHITE = 0;
    public static final int GRAY = 1;
    public static final int BLACK = 2;

    public int color;
    public int d;
    public Cuadro pi;

    private int id;
    private Bitmap image;
    private int tipo;

    private List<Cuadro> adj;

    public Cuadro(int x, int y, int tipo, Context context, int id){
        this.id = id;
        this.x = x;
        this.y = y;
        this.adj = new ArrayList<>();
        this.tipo = tipo;
        this.mContext = context;

        switch (tipo){
            case 0:
                this.image = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bloque_piso);
                break;
            case 1:
                this.image = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bloque_solido);
                break;
            case 2:
                this.image = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bloque_feo);
                break;
            case 3:
                this.image = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bloque_fuerte);
                break;
            default:
                this.image = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bloque_feo);
                break;
        }
    }

    public int getId()
    {
        return id;
    }

    public int getTipo()
    {
        return this.tipo;
    }

    public void addAdj(Cuadro cuadro)
    {
        adj.add(cuadro);
    }

    public List<Cuadro> getAdj()
    {
        return this.adj;
    }

    public void update() {}

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }


}
