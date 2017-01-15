package com.rafael.bomberman;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 21/12/16.
 */

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 208; // 13 bloques de 16 pixeles
    public static final int HEIGHT = 112; // 7 bloques de 16 pixeles
    private MainThread mainThread;
    private Background bg;
    private Player player;
    private Context mContext;

    private float unidadX, unidadY;
    private int board[][];

    public GameSurface(Context context) {
        super(context);

        mContext = context;
        getHolder().addCallback(this);
        mainThread = new MainThread(getHolder(), this);

        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        bg = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.bg1));
        bg = new Background(mContext);
        List<Cuadro> cuadroList = bg.getCuadroList();

//        for(int i = 0; i < bg.bloquesy; i++)
//        {
//            for(int j = 0; j < bg.bloquesx; j++)
//            {
//                Cuadro cuadro = cuadroList.get(i*bg.bloquesx + j);
//                board[i][j] = cuadro.getTipo();
//            }
//        }

        player = new Player(1, BitmapFactory.decodeResource(getResources(), R.drawable.zelda), 16, 16, 4);

        DisplayMetrics metrics = new DisplayMetrics();
        ((MainActivity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        unidadX = metrics.widthPixels/(bg.bloquesx*1.0f);
        unidadY = metrics.heightPixels/(bg.bloquesy*1.0f);

        mainThread.startRunning();
        mainThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while (retry)
        {
            try {
                mainThread.stopRunning();
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            retry = false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            int x = (int) (event.getX()/unidadX);
            int y = (int) (event.getY()/unidadY);

            Cuadro objetivo = bg.getCuadroList().get(x*bg.bloquesy + y);

            x = x*16;
            y = y*16;

            float xPlayer = player.getX();
            float yPlayer = player.getY();

            int posX = (int) xPlayer/16;
            int posY = (int) yPlayer/16;
            Cuadro s = bg.getCuadroList().get(posX*bg.bloquesy + posY);

            if(xPlayer == x && yPlayer == y)
            {
                Log.i("My app", "Bomba!!!");
            }
            else
            {
                boolean caminoEncontrado = BFS(s, objetivo);

                if(caminoEncontrado)
                {
                    JugadorRunnable runnable = new JugadorRunnable(player, objetivo);
                    Thread t = new Thread(runnable);
                    t.start();

                }
            }


        }

        return super.onTouchEvent(event);
    }

    public void update()
    {
        bg.update();
        player.update();
    }

    @Override
    public void draw(Canvas canvas) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((MainActivity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        final float scaleFactoryX = metrics.widthPixels/(WIDTH*1.0f);
        final float scaleFactoryY = metrics.heightPixels/(HEIGHT*1.0f);

        if(canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactoryX,scaleFactoryY);
            bg.draw(canvas);
            player.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    public Player getPlayer(){
        return this.player;
    }

    public float[][] getCamino(float x, float y, int xFinal, int yFinal)
    {
        return null;
    }

    public boolean BFS(Cuadro s, Cuadro objetivo)
    {
        List<Cuadro> grafo = bg.getCuadroList();

        for(Cuadro cuadro : grafo)
        {
            if(cuadro.getId() != s.getId())
            {
                cuadro.color = Cuadro.WHITE;
                cuadro.d = 1000;
                cuadro.pi = null;
            }
        }

        s.color = Cuadro.GRAY;
        s.d = 0;
        s.pi = null;

        List<Cuadro> Q = new ArrayList<>();
        Q.add(s);

        while(!Q.isEmpty())
        {
            Cuadro u = Q.remove(Q.size()-1);

            if(u.getId() == objetivo.getId())
            {
                return true;
            }

            for(Cuadro v : u.getAdj())
            {
                if(v.color == Cuadro.WHITE)
                {
                    v.color = Cuadro.GRAY;
                    v.d = u.d + 1;
                    v.pi = u;

                    Q.add(v);
                }
            }

            u.color = Cuadro.BLACK;
        }

        return false;
    }
}
