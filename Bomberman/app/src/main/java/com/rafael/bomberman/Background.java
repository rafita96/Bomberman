package com.rafael.bomberman;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 28/12/16.
 */

public class Background {

    public static final int bloquesx = 13;
    public static final int bloquesy = 7;
    private Context mContext;
    private List<Cuadro> cuadroList;

    public Background(Context context){
        this.mContext = context;
        cuadroList = new ArrayList<>();

        int contador = 0;

        for(int j = 0; j < bloquesx; j++){
            for(int i = 0; i < bloquesy; i++){

                Cuadro bloque;
                int y = 16*i;
                int x = 16*j;

                if(i == 0 || i == bloquesy-1 || j == 0 || j == bloquesx-1 ){
                    bloque = new Cuadro(x, y, 3, mContext, contador);
                }
                else if( i%2 == 0 && j%2 == 0 ){
                    bloque = new Cuadro(x, y, 1, mContext, contador);
                }
                else if( i == 1 && j == 1 || i%bloquesy == bloquesy-2 && j%bloquesx == bloquesx-2 || i%bloquesy == bloquesy - 2 && j == 1 || i == 1 && j%bloquesx == bloquesx-2 ){
                    bloque = new Cuadro(x, y, 0, mContext, contador);
                }
                else if( i == 1 && j == 2 || i%bloquesy == bloquesy-2 && j%bloquesx == bloquesx-3 || i%bloquesy == bloquesy - 2 && j == 2 || i == 1 && j%bloquesx == bloquesx-3 ){
                    bloque = new Cuadro(x, y, 0, mContext, contador);
                }
                else if( i == 2 && j == 1 || i%bloquesy == bloquesy-3 && j%bloquesx == bloquesx-2 || i%bloquesy == bloquesy - 3 && j == 1 || i == 2 && j%bloquesx == bloquesx-2 ){
                    bloque = new Cuadro(x, y, 0, mContext, contador);
                }
                else {
                    if(Math.random() < 0.8){
                        bloque = new Cuadro(x, y, 2, mContext, contador);
                    }
                    else {
                        bloque = new Cuadro(x, y, 0, mContext, contador);
                    }

                }

                contador++;
                cuadroList.add(bloque);
            }
        }

        adjCuadros();
    }

    public void adjCuadros()
    {
        for(Cuadro cuadro : cuadroList)
        {
            int id = cuadro.getId();
            Cuadro temp;

            if(cuadro.getTipo() == 0) {

                temp = cuadroList.get(id+1);

                if(temp.getTipo() == 0)
                {
                    cuadro.addAdj(temp);
                }

                temp = cuadroList.get(id-1);

                if(temp.getTipo() == 0)
                {
                    cuadro.addAdj(temp);
                }


                temp = cuadroList.get(id+bloquesy);

                if(temp.getTipo() == 0)
                {
                    cuadro.addAdj(temp);
                }

                temp = cuadroList.get(id-bloquesy);

                if(temp.getTipo() == 0)
                {
                    cuadro.addAdj(temp);
                }
            }
        }
    }

    public List<Cuadro> getCuadroList()
    {
        return this.cuadroList;
    }

    public void update(){}

    public void draw(Canvas canvas){
//        canvas.drawBitmap(image, x, y, null);
        for(Cuadro cuadro : cuadroList)
        {
            cuadro.draw(canvas);
        }
    }
}
