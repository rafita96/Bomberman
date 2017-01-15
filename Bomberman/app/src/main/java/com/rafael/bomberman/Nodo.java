package com.rafael.bomberman;

import java.util.List;

/**
 * Created by root on 12/01/17.
 */

public class Nodo {

    public static final int WHITE = 0;
    public static final int GRAY = 1;
    public static final int BLACK = 2;
    private int x, y;
    private int d;
    private int pi;

    private List<Nodo> adj;

    public Nodo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setD(int d){
        this.d = d;
    }

    public int getD(){
        return this.d;
    }

    public void setPi(int pi){
        this.pi = pi;
    }

    public int getPi(){
        return this.pi;
    }

    public void addAdj(Nodo nodo)
    {
        adj.add(nodo);
    }

    public List<Nodo> getAdj()
    {
        return this.adj;
    }
}
