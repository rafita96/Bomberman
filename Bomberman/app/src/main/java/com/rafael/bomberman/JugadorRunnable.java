package com.rafael.bomberman;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 14/01/17.
 */

public class JugadorRunnable implements Runnable {

    private Player jugador;
    private Cuadro objetivo;
    private List<Cuadro> queue;

    public JugadorRunnable(Player jugador, Cuadro objetivo)
    {
        this.jugador = jugador;
        this.objetivo = objetivo;

        queue = new ArrayList<>();
        queue.add(objetivo);

        Cuadro pi = objetivo.pi;
        while (pi != null)
        {
            queue.add(pi);
            pi = pi.pi;
        }
    }

    @Override
    public void run() {

        while (!queue.isEmpty())
        {
            Cuadro pi = queue.remove(queue.size()-1);
            try {
                Thread.sleep(jugador.getSpeed()*100);
                jugador.setX(pi.getX());
                jugador.setY(pi.getY());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
