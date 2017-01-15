package com.rafael.bomberman;

/**
 * Created by root on 18/12/16.
 */

public class Cuadro {
    /* tipos:
        0 - piso normal.
        1 - bloque solido
        2 - bloque debil
        3 - bloque fuerte
    */
    private int tipo;
    private boolean imageCharacter;

    public Cuadro(int tipo)
    {
        this.tipo = tipo;
        this.imageCharacter = false;
    }

    public void setTipo(int tipo)
    {
        this.tipo = tipo;
    }

    public int getTipo()
    {
        return this.tipo;
    }

    public boolean hasImageCharacter()
    {
        return imageCharacter;
    }

    public void setImageCharacter(boolean imageCharacter)
    {
        this.imageCharacter = imageCharacter;
    }
}
